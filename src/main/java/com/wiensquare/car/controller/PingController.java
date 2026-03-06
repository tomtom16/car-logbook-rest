package com.wiensquare.car.controller;

import com.wiensquare.car.dto.User;
import com.wiensquare.car.rest.openapi.server.api.PingApi;
import com.wiensquare.car.rest.openapi.server.model.PingResponseModel;
import com.wiensquare.car.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
@RequiredArgsConstructor
public class PingController implements PingApi {

    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @Override
    public ResponseEntity<PingResponseModel> ping() {
        return ResponseEntity.ok(getPingResponseModel());
    }

    private PingResponseModel getPingResponseModel() {
        PingResponseModel response = new PingResponseModel();

        response.setText("pong");
        response.setTimestamp(OffsetDateTime.now());

        User currentUser = userService.getCurrentUser();
        if (currentUser != null) {
            response.setUsername(currentUser.getUsername());
            response.setUid(currentUser.getUid());
        }

        return response;
    }

}
