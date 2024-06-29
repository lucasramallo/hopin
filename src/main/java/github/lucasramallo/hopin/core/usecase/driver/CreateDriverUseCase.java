package github.lucasramallo.hopin.core.usecase.driver;

import github.lucasramallo.hopin.api.dtos.driver.CreateDriverRequestDTO;
import github.lucasramallo.hopin.core.domain.cab.Cab;
import github.lucasramallo.hopin.core.domain.driver.Driver;
import github.lucasramallo.hopin.core.domain.driver.util.DriverValidations;
import github.lucasramallo.hopin.core.usecase.cab.CreateCabUseCase;
import github.lucasramallo.hopin.data.jpa.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Driver execute(CreateDriverRequestDTO createDriverRequestDTO) {
        DriverValidations.validateName(createDriverRequestDTO.name());
        DriverValidations.validateAge(createDriverRequestDTO.dateOfBirth());

        Cab driverCab = createCabUseCase.execute(createDriverRequestDTO);
        Driver newDriver = new Driver();
        newDriver.setId(UUID.randomUUID());
        newDriver.setName(createDriverRequestDTO.name());
        newDriver.setPassword(createDriverRequestDTO.password());
        newDriver.setDateOfBirth(createDriverRequestDTO.dateOfBirth());
        newDriver.setCab(driverCab);
        newDriver.setCreatedAt(LocalDateTime.now());

        repository.save(newDriver);

        return newDriver;
    }
}