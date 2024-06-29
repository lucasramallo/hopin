package github.lucasramallo.hopin.api.dtos.driver;

import com.fasterxml.jackson.annotation.JsonProperty;
import github.lucasramallo.hopin.api.dtos.trip.TripResponseDTO;

import java.util.List;

public record DriverTripsResponseDTO(
        List<TripResponseDTO> trips
) {
}
