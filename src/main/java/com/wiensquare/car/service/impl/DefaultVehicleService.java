package com.wiensquare.car.service.impl;

import com.wiensquare.car.domain.entities.Vehicle;
import com.wiensquare.car.domain.repositories.VehicleRepository;
import com.wiensquare.car.dto.User;
import com.wiensquare.car.rest.openapi.server.model.VehicleModel;
import com.wiensquare.car.rest.openapi.server.model.VehiclePowerModel;
import com.wiensquare.car.service.UserService;
import com.wiensquare.car.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class DefaultVehicleService implements VehicleService {

    private static final BigDecimal KW_HP_FACTOR = BigDecimal.valueOf(1.35962);

    private final UserService userService;

    private final VehicleRepository vehicleRepository;

    /**
     * get vehicles for current user
     *
     * @return {@link List} of {@link VehicleModel}
     */
    @Override
    public List<VehicleModel> getVehicles() {
        User currentUser = userService.getCurrentUser();
        if (currentUser != null) {
            return getVehicles(currentUser.getUid());
        }
        return List.of();
    }

    /**
     * get primary vehicle for current user
     *
     * @return {@link VehicleModel}
     */
    @Override
    public VehicleModel getPrimaryVehicle() {
        User currentUser = userService.getCurrentUser();
        if (currentUser != null) {
            return getPrimaryVehicle(currentUser.getUid());
        }
        return null;
    }

    @Override
    public List<VehicleModel> getVehicles(UUID uid) {
        List<Vehicle> userVehicles = vehicleRepository.findByUserId(uid);
        return userVehicles.stream().map(this::mapVehicle).toList();
    }

    @Override
    public VehicleModel getPrimaryVehicle(UUID uid) {
        Vehicle primaryUserVehicle = vehicleRepository.findByUserIdAndPrimary(uid, true);
        return mapVehicle(primaryUserVehicle);
    }

    private VehicleModel mapVehicle(Vehicle vehicle) {
        VehicleModel data = new VehicleModel();

        data.setId(vehicle.getId());
        data.setMake(vehicle.getMake());
        data.setModel(vehicle.getModel());
        data.setLicensePlate(vehicle.getLicensePlate());
        data.setPhoto(vehicle.getPhoto());
        data.setYearOfConstruction(vehicle.getYearOfConstruction());
        data.setGasoline(vehicle.getGasolineType());

        int kw = vehicle.getPower(); // kw
        int hp = BigDecimal.valueOf(kw).multiply(KW_HP_FACTOR).intValue();
        VehiclePowerModel power = new VehiclePowerModel();
        power.setKw(kw);
        power.setHp(hp);
        data.setPower(power);

        return data;
    }
}
