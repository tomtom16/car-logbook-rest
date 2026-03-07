package com.wiensquare.car.controller;

import com.wiensquare.car.rest.openapi.server.api.LogbookApi;
import com.wiensquare.car.rest.openapi.server.model.LogbookEntryModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class LogbookController implements LogbookApi {

    @PreAuthorize("isAuthenticated()")
    @Override
    public ResponseEntity<List<LogbookEntryModel>> getEntries(UUID vehicleId) {
        return null;
    }

}
