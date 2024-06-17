package github.lucasramallo.hopin.api.dtos.customer;

import github.lucasramallo.hopin.core.domain.customer.Customer;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateCustomerResponseDTO(
        UUID id,
        String name,
        String email,
        LocalDateTime createdAt
) {
}
