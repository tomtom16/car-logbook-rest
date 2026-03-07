package com.wiensquare.car.service.impl;

import com.wiensquare.car.domain.entities.LogbookEntry;
import com.wiensquare.car.domain.entities.Vehicle;
import com.wiensquare.car.domain.repositories.LogbookEntryRepository;
import com.wiensquare.car.domain.repositories.VehicleRepository;
import com.wiensquare.car.dto.User;
import com.wiensquare.car.rest.openapi.server.model.LogbookEntryModel;
import com.wiensquare.car.service.LogbookService;
import com.wiensquare.car.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultLogbookService implements LogbookService {

    private final LogbookEntryRepository logbookEntryRepository;

    private final VehicleRepository vehicleRepository;

    private final UserService userService;

    @Override
    public List<LogbookEntryModel> getLogbookEntries(UUID vehicleId) {
        User currentUser = userService.getCurrentUser();
        Optional<Vehicle> vehicle = vehicleRepository.findByIdAndUserId(vehicleId, currentUser.getUid());
        return vehicle.map(value -> value.getLogbookEntries().stream().map(this::mapEntry).toList()).orElse(null);
    }

    @Override
    public LogbookEntryModel createLogbookEntry(LogbookEntryModel data) {
        LogbookEntry entry = mapEntity(data);
        entry = logbookEntryRepository.save(entry);
        return mapEntry(entry);
    }

    private LogbookEntryModel mapEntry(LogbookEntry entry) {
        LogbookEntryModel data = new LogbookEntryModel();

        data.setVehicle(entry.getVehicle().getId());
        data.setStartDate(entry.getStartTime());
        data.setEndDate(entry.getEndTime());
        data.setKmStart(entry.getKmStart());
        data.setKmEnd(entry.getKmEnd());
        data.setTrip(entry.getTrip().doubleValue());
        data.setRoute(entry.getRoute());
        data.setComment(entry.getComment());

        return data;
    }

    private LogbookEntry mapEntity(LogbookEntryModel data) {
        LogbookEntry entry = new LogbookEntry();

        entry.setStartTime(data.getStartDate());
        entry.setEndTime(data.getEndDate());
        entry.setKmStart(data.getKmStart());
        entry.setKmEnd(data.getKmEnd());
        if (data.getTrip() != null) {
            entry.setTrip(BigDecimal.valueOf(data.getTrip()));
        } else {
            entry.setTrip(BigDecimal.ZERO);
        }
        User currentUser = userService.getCurrentUser();
        Optional<Vehicle> vehicle = vehicleRepository.findByIdAndUserId(data.getVehicle(), currentUser.getUid());
        if (vehicle.isPresent()) {
            entry.setVehicle(vehicle.get());
        } else {
            throw new EntityNotFoundException(String.format("Vehicle with id %s not found for current user %s!", data.getVehicle(), currentUser.getUid()));
        }

        entry.setRoute(data.getRoute());
        entry.setComment(data.getComment());

        return entry;
    }
}
