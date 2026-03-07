package com.wiensquare.car.service;

import com.wiensquare.car.rest.openapi.server.model.LogbookEntryModel;

import java.util.List;
import java.util.UUID;

public interface LogbookService {

    List<LogbookEntryModel> getLogbookEntries(UUID vehicleId);

}
