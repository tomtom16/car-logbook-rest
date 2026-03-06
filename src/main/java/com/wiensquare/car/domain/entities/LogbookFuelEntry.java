package com.wiensquare.car.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Setter
@Getter
@ToString
public class LogbookFuelEntry extends BaseEntity {

    @ManyToOne
    private Vehicle vehicle;

    private OffsetDateTime date;

    @Column(columnDefinition = "SMALLINT")
    private Integer mileageStatus;

    private BigDecimal trip;

    private BigDecimal liters;

    private BigDecimal price;

    private String comment;

}
