package github.lucasramallo.hopin.api.dtos.customer;

import github.lucasramallo.hopin.core.domain.customer.Customer;
import github.lucasramallo.hopin.core.domain.trips.Trip;
import jakarta.persistence.Column;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record CustomerInformationResponseDTO(
        UUID Id,
        String name,
        String email,
        LocalDateTime createdAt,
        ArrayList<Trip> trips
) {
}
