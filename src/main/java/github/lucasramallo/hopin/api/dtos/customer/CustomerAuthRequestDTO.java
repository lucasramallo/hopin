package github.lucasramallo.hopin.api.dtos.customer;
import jakarta.annotation.Nonnull;
public record CustomerAuthRequestDTO(
        String email,

        String password
) {
}
