package github.lucasramallo.hopin.api.dtos.customer;

import github.lucasramallo.hopin.api.dtos.trip.TripResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CustomerInformationResponseDTO(
        UUID Id,
        String name,
        String email,
        LocalDateTime createdAt
) {
}
