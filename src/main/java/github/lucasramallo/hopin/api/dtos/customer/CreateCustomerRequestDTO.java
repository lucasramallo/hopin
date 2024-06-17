package github.lucasramallo.hopin.api.dtos.customer;

public record CreateCustomerRequestDTO(
        String name,
        String email,
        String password
) {
}
