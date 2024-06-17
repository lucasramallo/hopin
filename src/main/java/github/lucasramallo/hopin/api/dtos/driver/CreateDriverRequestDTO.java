package github.lucasramallo.hopin.api.dtos.driver;
import java.time.LocalDate;


public record CreateDriverRequestDTO(
        String name,
        String password,
        LocalDate dateOfBirth,
        String model,
        String color,
        String plateNum
) {
}
