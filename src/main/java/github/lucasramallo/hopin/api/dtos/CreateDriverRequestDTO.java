package github.lucasramallo.hopin.api.dtos;
import java.time.LocalDate;


public record CreateDriverRequestDTO(
        String name,
        LocalDate dateOfBirth,
        String model,
        String color,
        String plateNum
) {
}
