package github.lucasramallo.hopin.api.dtos.trip;

import github.lucasramallo.hopin.core.domain.cab.Cab;

import java.time.LocalDateTime;
import java.util.UUID;

public record TripDriverResponseDTO(
        UUID id,
        String name,
        Cab cab
) {
}
