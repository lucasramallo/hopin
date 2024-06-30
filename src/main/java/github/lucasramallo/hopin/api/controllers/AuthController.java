package github.lucasramallo.hopin.api.controllers;

import github.lucasramallo.hopin.api.dtos.customer.CreateCustomerRequestDTO;
import github.lucasramallo.hopin.api.dtos.customer.CustomerResponseDTO;
import github.lucasramallo.hopin.api.dtos.driver.CreateDriverRequestDTO;
import github.lucasramallo.hopin.api.dtos.driver.DriverResponseDTO;
import github.lucasramallo.hopin.core.domain.customer.Customer;
import github.lucasramallo.hopin.core.domain.driver.Driver;
import github.lucasramallo.hopin.core.usecase.customer.CreateCustomerUseCase;
import github.lucasramallo.hopin.core.usecase.driver.CreateDriverUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private CreateCustomerUseCase createCustomerUseCase;

    @Autowired
    private CreateDriverUseCase createDriverUseCase;

    @PostMapping("/signup/customer")
    public ResponseEntity<CustomerResponseDTO> CreateCustomer(@RequestBody CreateCustomerRequestDTO requestDTO) {
        Customer customer = createCustomerUseCase.execute(requestDTO);
        CustomerResponseDTO responseDTO = new CustomerResponseDTO(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getCreatedAt()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PostMapping("/signup/driver")
    public ResponseEntity<DriverResponseDTO> createDriver(@RequestBody CreateDriverRequestDTO requestDTO) {
        Driver driver = createDriverUseCase.execute(requestDTO);
        DriverResponseDTO responseDTO = new DriverResponseDTO(
                driver.getId(),
                driver.getName(),
                driver.getDateOfBirth(),
                driver.getCab(),
                driver.getCreatedAt()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}