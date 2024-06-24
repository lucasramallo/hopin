package github.lucasramallo.hopin.api.controllers;

import github.lucasramallo.hopin.api.dtos.customer.CreateCustomerRequestDTO;
import github.lucasramallo.hopin.api.dtos.customer.CreateCustomerResponseDTO;
import github.lucasramallo.hopin.api.dtos.customer.CustomerInformationRequestDTO;
import github.lucasramallo.hopin.api.dtos.customer.CustomerInformationResponseDTO;
import github.lucasramallo.hopin.core.domain.customer.Customer;
import github.lucasramallo.hopin.core.usecase.customer.CreateCustomerUseCase;
import github.lucasramallo.hopin.core.usecase.driver.GetCustomerProfileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CreateCustomerUseCase createCustomerUseCase;

    @Autowired
    private GetCustomerProfileInfo getCustomerProfileInfo;

    @PostMapping()
    public ResponseEntity<CreateCustomerResponseDTO> CreateCustomer(@RequestBody CreateCustomerRequestDTO requestDTO) {
        Customer customer = createCustomerUseCase.execute(requestDTO);
        CreateCustomerResponseDTO responseDTO = new CreateCustomerResponseDTO(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getCreatedAt()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("profile/{userId}")
    public ResponseEntity<CustomerInformationResponseDTO> GetCustomerrProfileInfo(@PathVariable UUID userId) {
        CustomerInformationResponseDTO responseDTO = getCustomerProfileInfo.execute(new CustomerInformationRequestDTO(userId));
        return ResponseEntity.ok(responseDTO);
    }
}
