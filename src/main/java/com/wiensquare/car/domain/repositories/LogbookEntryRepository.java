package com.wiensquare.car.domain.repositories;

import com.wiensquare.car.domain.entities.LogbookEntry;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LogbookEntryRepository extends JpaRepository<LogbookEntry, UUID> {

    List<LogbookEntry> findByVehicleId(UUID vehicleId, Pageable page);

}
