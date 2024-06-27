package github.lucasramallo.hopin.api.dtos.customer;

import java.time.LocalDateTime;
import java.util.UUID;

public record CustomerResponseDTO(
        UUID id,
        String name,
        String email,
        LocalDateTime createdAt
) {
}