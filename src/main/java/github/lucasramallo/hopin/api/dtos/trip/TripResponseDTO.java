package github.lucasramallo.hopin.api.dtos.trip;

import com.fasterxml.jackson.annotation.JsonProperty;
import github.lucasramallo.hopin.api.dtos.customer.CustomerResponseDTO;
import github.lucasramallo.hopin.api.dtos.driver.DriverResponseDTO;
import github.lucasramallo.hopin.core.domain.payments.Method;
import github.lucasramallo.hopin.core.domain.payments.Payment;
import github.lucasramallo.hopin.core.domain.trips.Status;

import java.time.LocalDateTime;
import java.util.UUID;

public record TripResponseDTO(
        UUID id,
        @JsonProperty("customer")
        TripCustomerResponseDTO customerResponseDTO,
        @JsonProperty("driver")
        TripDriverResponseDTO driverResponseDTO,
        Payment payment,
        Status tripStatus,
        String source,
        String destination,
        LocalDateTime createdAt
) {
}
