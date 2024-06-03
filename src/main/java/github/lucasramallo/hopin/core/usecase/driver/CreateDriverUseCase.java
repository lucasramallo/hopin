package github.lucasramallo.hopin.core.usecase.driver;

import github.lucasramallo.hopin.api.dtos.CreateDriverRequestDTO;
import github.lucasramallo.hopin.core.domain.driver.Driver;
import github.lucasramallo.hopin.data.jpa.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CreateDriverUseCase {
    @Autowired
    private DriverRepository repository;

    public void excecute(CreateDriverRequestDTO createDriverRequestDTO) {
        Driver newDriver = new Driver();
        newDriver.setId(UUID.randomUUID());
        newDriver.setName(createDriverRequestDTO.name());
        newDriver.setDateOfBirth(createDriverRequestDTO.dateOfBirth());
        newDriver.setCreatedAt(LocalDateTime.now());

        repository.save(newDriver);
    }
}