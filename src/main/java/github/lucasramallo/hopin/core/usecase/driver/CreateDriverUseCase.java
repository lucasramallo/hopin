package github.lucasramallo.hopin.core.usecase.driver;

import github.lucasramallo.hopin.api.dtos.CreateDriverRequestDTO;
import github.lucasramallo.hopin.core.domain.cab.Cab;
import github.lucasramallo.hopin.core.domain.driver.Driver;
import github.lucasramallo.hopin.core.usecase.cab.CreateCabUseCase;
import github.lucasramallo.hopin.data.jpa.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CreateDriverUseCase {
    @Autowired
    private DriverRepository repository;
    @Autowired
    private CreateCabUseCase createCabUseCase;

    public void excecute(CreateDriverRequestDTO createDriverRequestDTO) {
        Cab driverCab = createCabUseCase.execute(createDriverRequestDTO);

        Driver newDriver = new Driver();
        newDriver.setId(UUID.randomUUID());
        newDriver.setName(createDriverRequestDTO.name());
        newDriver.setDateOfBirth(createDriverRequestDTO.dateOfBirth());
        newDriver.setCab(driverCab);
        newDriver.setCreatedAt(LocalDateTime.now());

        repository.save(newDriver);
    }
}