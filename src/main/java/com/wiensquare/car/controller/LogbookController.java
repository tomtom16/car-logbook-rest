package com.wiensquare.car.controller;

import com.wiensquare.car.rest.openapi.server.api.LogbookApi;
import com.wiensquare.car.rest.openapi.server.model.LogbookEntryModel;
import com.wiensquare.car.service.LogbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class LogbookController implements LogbookApi {

    private final LogbookService logbookService;

    @PreAuthorize("isAuthenticated()")
    @Override
    public ResponseEntity<List<LogbookEntryModel>> getEntries(UUID vehicleId) {
        try {
            return ResponseEntity.ok(logbookService.getLogbookEntries(vehicleId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public ResponseEntity<LogbookEntryModel> createEntry(UUID vehicleId, LogbookEntryModel data) {
        try {
            return ResponseEntity.ok(logbookService.createLogbookEntry(data));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
