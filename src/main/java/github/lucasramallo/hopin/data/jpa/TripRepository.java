package github.lucasramallo.hopin.data.jpa;

import github.lucasramallo.hopin.core.domain.trips.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TripRepository extends JpaRepository<Trip, UUID> {
    ArrayList<Trip> findAllByCustomerId(UUID customerId);
}