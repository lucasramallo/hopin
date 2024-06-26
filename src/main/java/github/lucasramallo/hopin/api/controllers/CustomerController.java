package github.lucasramallo.hopin.api.controllers;

import github.lucasramallo.hopin.api.dtos.customer.*;
import github.lucasramallo.hopin.core.domain.customer.Customer;
import github.lucasramallo.hopin.core.usecase.customer.CreateCustomerUseCase;
import github.lucasramallo.hopin.core.usecase.customer.EditCustumerUseCase;
import github.lucasramallo.hopin.core.usecase.customer.GetCustomerProfileInfo;
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
    private GetCustomerProfileInfo getCustomerProfileInfo;

    @Autowired
    private EditCustumerUseCase editCustumerUseCase;

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

    @PutMapping("/profile/{userId}")
    public ResponseEntity<Any> editCustomer(@PathVariable UUID userId, @RequestBody EditCustomerResquestDTO resquestDTO) {
        editCustumerUseCase.execute(userId, resquestDTO);
        return ResponseEntity.ok().build();
    }
}
