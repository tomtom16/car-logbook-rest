package com.wiensquare.car.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class User {

    private UUID uid;
    private String username;

}
