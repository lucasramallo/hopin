package github.lucasramallo.hopin.api.dtos.customer;

import github.lucasramallo.hopin.api.dtos.trip.TripResponseDTO;
import github.lucasramallo.hopin.core.domain.trips.Trip;

import java.util.ArrayList;
import java.util.List;

public record CustomerTripsResponseDTO (
       List<TripResponseDTO> trips
) {
}
