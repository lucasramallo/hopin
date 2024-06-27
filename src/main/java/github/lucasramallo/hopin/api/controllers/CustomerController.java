package github.lucasramallo.hopin.api.controllers;

import github.lucasramallo.hopin.api.dtos.customer.*;
import github.lucasramallo.hopin.core.domain.customer.Customer;
import github.lucasramallo.hopin.core.usecase.customer.CreateCustomerUseCase;
import github.lucasramallo.hopin.core.usecase.customer.EditCustumerUseCase;
import github.lucasramallo.hopin.core.usecase.customer.GetCustomerProfileInfoUseCase;
import org.hibernate.mapping.Any;
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
    private GetCustomerProfileInfoUseCase getCustomerProfileInfoUseCase;

    @Autowired
    private EditCustumerUseCase editCustumerUseCase;

    @PostMapping()
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

    @GetMapping("profile/{userId}")
    public ResponseEntity<CustomerInformationResponseDTO> GetCustomerProfileInfo(@PathVariable UUID userId) {
        CustomerInformationResponseDTO responseDTO = getCustomerProfileInfoUseCase.execute(new CustomerInformationRequestDTO(userId));
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/profile/{userId}")
    public ResponseEntity<Any> editCustomer(@PathVariable UUID userId, @RequestBody EditCustomerResquestDTO resquestDTO) {
        editCustumerUseCase.execute(userId, resquestDTO);
        return ResponseEntity.ok().build();
    }
}
