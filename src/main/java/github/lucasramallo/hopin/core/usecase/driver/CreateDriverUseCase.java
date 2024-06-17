package github.lucasramallo.hopin.core.usecase.driver;

import github.lucasramallo.hopin.api.dtos.CreateDriverRequestDTO;
import github.lucasramallo.hopin.core.domain.cab.Cab;
import github.lucasramallo.hopin.core.domain.driver.Driver;
import github.lucasramallo.hopin.core.usecase.cab.CreateCabUseCase;
import github.lucasramallo.hopin.data.jpa.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CreateDriverUseCase {
    @Autowired
    private DriverRepository repository;
    @Autowired
    private CreateCabUseCase createCabUseCase;

    public Driver execute(CreateDriverRequestDTO createDriverRequestDTO) {
        Cab driverCab = createCabUseCase.execute(createDriverRequestDTO);
        Driver newDriver = new Driver();

        newDriver.setId(UUID.randomUUID());

        validateName(createDriverRequestDTO.name());
        newDriver.setName(createDriverRequestDTO.name());

        validateAge(createDriverRequestDTO.dateOfBirth());
        newDriver.setDateOfBirth(createDriverRequestDTO.dateOfBirth());

        newDriver.setCab(driverCab);
        newDriver.setCreatedAt(LocalDateTime.now());

        repository.save(newDriver);

        return newDriver;
    }

    public void validateName(String name) {
        Pattern pattern = Pattern.compile("^[A-ZÀ-ÿ][A-Za-zÀ-ÿ ]{0,28}$");
        Matcher matcher = pattern.matcher(name);

        if (!matcher.matches()) {
            throw new RuntimeException("Invalid name!");
        }
    }

    public void validateAge(LocalDate age) {
        int driverAge = Period.between(age, LocalDate.now()).getYears();

        if(driverAge < 18) {
            throw new RuntimeException("O motorista deve ser maior de idade!");
        }
    }
}