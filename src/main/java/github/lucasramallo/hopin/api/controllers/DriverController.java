package github.lucasramallo.hopin.api.controllers;

import github.lucasramallo.hopin.api.dtos.driver.*;
import github.lucasramallo.hopin.core.domain.driver.Driver;
import github.lucasramallo.hopin.core.usecase.driver.CreateDriverUseCase;
import github.lucasramallo.hopin.core.usecase.driver.EditDriverUseCase;
import github.lucasramallo.hopin.core.usecase.driver.GetDriverProfileInfoUseCase;

import github.lucasramallo.hopin.core.usecase.driver.GetDriverTripsUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/driver")
public class DriverController {
    @Autowired
    private GetDriverProfileInfoUseCase getDriverProfileInfoUseCase;

    @Autowired
    private EditDriverUseCase editDriverUseCase;

    @Autowired
    private GetDriverTripsUseCase getDriverTripsUseCase;

    @GetMapping("/profile/{id}")
    public ResponseEntity<DriverInformationResponseDTO> GetCustomerProfileInfo(@PathVariable UUID id) {
        DriverInformationResponseDTO responseDTO = getDriverProfileInfoUseCase.execute(new DriverInformationRequestDTO(id));
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/trips/{id}")
    public ResponseEntity<DriverTripsResponseDTO> GetDriverTrips(
            @PathVariable UUID id,
            @RequestParam int page,
            @RequestParam int size
    ) {
        DriverTripsResponseDTO responseDTO = getDriverTripsUseCase.execute(new DriverInformationRequestDTO(id), page, size);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/profile/{id}")
    public ResponseEntity<DriverInformationResponseDTO> editDriver(@PathVariable UUID id, @RequestBody EditDriverRequestDTO requestDTO) {
        editDriverUseCase.execute(id, requestDTO);
        return ResponseEntity.ok().build();
    }
}