package com.wiensquare.car.controller;

import com.wiensquare.car.rest.openapi.server.api.VehicleApi;
import com.wiensquare.car.rest.openapi.server.model.VehicleModel;
import com.wiensquare.car.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class VehicleController implements VehicleApi {

    private final VehicleService vehicleService;

    @PreAuthorize("isAuthenticated()")
    @Override
    public ResponseEntity<List<VehicleModel>> getVehicles() {
        try {
            return ResponseEntity.ok(vehicleService.getVehicles());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public ResponseEntity<VehicleModel> createVehicle(VehicleModel data) {
        try {
            return ResponseEntity.ok(vehicleService.createVehicle(data));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
