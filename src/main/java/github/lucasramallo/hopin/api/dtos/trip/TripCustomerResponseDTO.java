package github.lucasramallo.hopin.api.dtos.trip;

import java.time.LocalDateTime;
import java.util.UUID;

public record TripCustomerResponseDTO(
        UUID id,
        String name,
        String email
) {
}
