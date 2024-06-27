package github.lucasramallo.hopin.api.dtos.driver;

import github.lucasramallo.hopin.core.domain.cab.Cab;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record DriverResponseDTO(
        UUID id,
        String name,
        LocalDate dateOfBirth,
        Cab cab,
        LocalDateTime createdAt
) {
}
