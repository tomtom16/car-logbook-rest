package com.wiensquare.car.domain.entities;

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
public class LogbookEntry extends BaseEntity {

    @ManyToOne
    private Vehicle vehicle;

    private OffsetDateTime startTime;

    private OffsetDateTime endTime;

    private Integer kmStart;

    private Integer kmEnd;

    private BigDecimal trip;

    private String route;

    private String comment;

}
