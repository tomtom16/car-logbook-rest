package com.wiensquare.car.service;

import com.wiensquare.car.rest.openapi.server.model.VehicleModel;

import java.util.List;
import java.util.UUID;

public interface VehicleService {

    List<VehicleModel> getVehicles();

    VehicleModel getPrimaryVehicle();

    List<VehicleModel> getVehicles(UUID uid);

    VehicleModel getPrimaryVehicle(UUID uid);

    VehicleModel createVehicle(VehicleModel vehicle);

}
