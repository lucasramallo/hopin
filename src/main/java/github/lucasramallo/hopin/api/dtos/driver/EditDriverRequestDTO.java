package github.lucasramallo.hopin.api.dtos.driver;
import java.time.LocalDate;
import java.util.UUID;

public record EditDriverRequestDTO(
        String name,
        LocalDate dateOfBirth
) {
}
