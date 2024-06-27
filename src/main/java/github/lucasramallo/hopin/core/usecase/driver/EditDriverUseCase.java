package github.lucasramallo.hopin.core.usecase.driver;

import github.lucasramallo.hopin.api.dtos.driver.EditDriverRequestDTO;
import github.lucasramallo.hopin.core.domain.driver.Driver;
import github.lucasramallo.hopin.core.domain.driver.util.DriverValidations;
import github.lucasramallo.hopin.data.jpa.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class EditDriverUseCase {
    @Autowired
    private DriverRepository repository;

    public void execute(UUID id, EditDriverRequestDTO requestDTO) {
        Optional<Driver> driver = repository.findById(id);

        if(driver.isEmpty()) {
            throw new RuntimeException("Driver not found!");
        }

        DriverValidations.validateName(requestDTO.name());
        DriverValidations.validateAge(requestDTO.dateOfBirth());

        driver.get().setName(requestDTO.name());
        driver.get().setDateOfBirth(requestDTO.dateOfBirth());

        repository.save(driver.get());
    }
}
