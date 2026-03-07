package com.wiensquare.car.service.impl;

import com.wiensquare.car.domain.entities.LogbookEntry;
import com.wiensquare.car.domain.repositories.LogbookEntryRepository;
import com.wiensquare.car.rest.openapi.server.model.LogbookEntryModel;
import com.wiensquare.car.service.LogbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultLogbookService implements LogbookService {

    private final LogbookEntryRepository logbookEntryRepository;

    @Override
    public List<LogbookEntryModel> getLogbookEntries(UUID vehicleId) {
        List<LogbookEntry> entries = logbookEntryRepository.findByVehicleId(vehicleId, null);
        return entries.stream().map(this::mapEntry).toList();
    }

    private LogbookEntryModel mapEntry(LogbookEntry entry) {
        LogbookEntryModel data = new LogbookEntryModel();

        data.setVehicle(entry.getVehicle().getId());
        data.setStartDate(entry.getStartTime());
        data.setEndDate(entry.getEndTime());
        data.setKmStart(entry.getKmStart());
        data.setKmEnd(entry.getKmEnd());
        data.setTrip(entry.getTrip().doubleValue());
        data.setRoute(entry.getRoute());
        data.setComment(entry.getComment());

        return data;
    }
}
