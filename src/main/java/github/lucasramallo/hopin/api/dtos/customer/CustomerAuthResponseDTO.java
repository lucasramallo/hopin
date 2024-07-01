package github.lucasramallo.hopin.api.dtos.customer;


public record CustomerAuthResponseDTO(
        String tokien,
        CustomerResponseDTO customer
) {
}
