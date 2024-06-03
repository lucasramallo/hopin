package github.lucasramallo.hopin.api.controllers;

import github.lucasramallo.hopin.api.dtos.CreateDriverRequestDTO;
import github.lucasramallo.hopin.core.usecase.driver.CreateDriverUseCase;
import org.hibernate.mapping.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
    public ResponseEntity<Any> createDriver(@RequestBody CreateDriverRequestDTO requestDTO) {
        createDriverUseCase.excecute(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
