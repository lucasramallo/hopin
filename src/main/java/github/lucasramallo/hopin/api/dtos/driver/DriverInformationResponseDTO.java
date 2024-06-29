package github.lucasramallo.hopin.api.dtos.driver;

import github.lucasramallo.hopin.api.dtos.trip.TripResponseDTO;
import github.lucasramallo.hopin.core.domain.cab.Cab;

import java.time.LocalDate;
import java.util.List;

public record DriverInformationResponseDTO(
        String name,
        LocalDate dateOfBirth,
        Cab cab
)
{}
