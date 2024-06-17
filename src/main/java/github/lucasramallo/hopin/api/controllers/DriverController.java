package github.lucasramallo.hopin.api.controllers;

import github.lucasramallo.hopin.api.dtos.driver.CreateDriverRequestDTO;
import github.lucasramallo.hopin.api.dtos.driver.CreateDriverResponseDTO;
import github.lucasramallo.hopin.core.domain.driver.Driver;
import github.lucasramallo.hopin.core.usecase.driver.CreateDriverUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/driver")
public class DriverController {
    @Autowired
    private CreateDriverUseCase createDriverUseCase;

    @PostMapping
    public ResponseEntity<CreateDriverResponseDTO> createDriver(@RequestBody CreateDriverRequestDTO requestDTO) {
        Driver driver = createDriverUseCase.execute(requestDTO);
        CreateDriverResponseDTO responseDTO = new CreateDriverResponseDTO(
                driver.getId(),
                driver.getName(),
                driver.getDateOfBirth(),
                driver.getCab(),
                driver.getCreatedAt()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
