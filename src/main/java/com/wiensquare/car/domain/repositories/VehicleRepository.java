package com.wiensquare.car.domain.repositories;

import com.wiensquare.car.domain.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {

    List<Vehicle> findByUserId(UUID userId);

    Optional<Vehicle> findByUserIdAndPrimary(UUID userId, boolean primary);

    Optional<Vehicle> findByIdAndUserId(UUID vehicleId, UUID uid);

}
