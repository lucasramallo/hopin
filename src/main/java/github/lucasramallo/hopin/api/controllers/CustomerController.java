package github.lucasramallo.hopin.api.controllers;

import github.lucasramallo.hopin.api.dtos.customer.CreateCustomerRequestDTO;
import github.lucasramallo.hopin.api.dtos.customer.CreateCustomerResponseDTO;
import github.lucasramallo.hopin.core.domain.customer.Customer;
import github.lucasramallo.hopin.core.usecase.customer.CreateCustomerUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CreateCustomerUseCase createCustomerUseCase;

    @PostMapping()
    public ResponseEntity<CreateCustomerResponseDTO> CreateCustomer(@RequestBody CreateCustomerRequestDTO requestDTO) {
        Customer customer = createCustomerUseCase.execute(requestDTO);
        CreateCustomerResponseDTO responseDTO = new CreateCustomerResponseDTO(
                customer.getID(),
                customer.getName(),
                customer.getEmail(),
                customer.getCreatedAt()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
