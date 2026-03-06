package com.wiensquare.car.domain.entities;

import com.wiensquare.car.domain.GasolineType;
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
    private GasolineType gasolineType;
    private String photo;

}
