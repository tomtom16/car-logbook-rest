package com.wiensquare.car.domain.entities;

import com.wiensquare.car.rest.openapi.server.model.VehicleModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Setter
@Getter
@ToString
public class Vehicle extends BaseEntity {

    private UUID userId;
    private String make;
    private String model;
    private String licensePlate;
    private LocalDate yearOfConstruction;
    @Column(columnDefinition = "SMALLINT")
    private Integer power;
    @Enumerated(EnumType.STRING)
    private VehicleModel.GasolineEnum gasolineType;
    private String photo;

    @Column(name = "primary_vehicle")
    private boolean primary;

}
