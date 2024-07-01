package github.lucasramallo.hopin.api.controllers;

import github.lucasramallo.hopin.api.dtos.customer.*;
import github.lucasramallo.hopin.core.domain.customer.Customer;
import github.lucasramallo.hopin.core.usecase.customer.CreateCustomerUseCase;
import github.lucasramallo.hopin.core.usecase.customer.EditCustumerUseCase;
import github.lucasramallo.hopin.core.usecase.customer.GetCustomerProfileInfoUseCase;
import github.lucasramallo.hopin.core.usecase.customer.GetCustomerTrips;
import jakarta.servlet.http.HttpServletRequest;
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
    private GetCustomerProfileInfoUseCase getCustomerProfileInfoUseCase;

    @Autowired
    private EditCustumerUseCase editCustumerUseCase;

    @Autowired
    private GetCustomerTrips getCustomerTrips;

    @GetMapping("/profile")
    public ResponseEntity<CustomerInformationResponseDTO> GetCustomerProfileInfo(HttpServletRequest request) {
        UUID customerId = UUID.fromString(request.getAttribute("user_id").toString());
        CustomerInformationResponseDTO responseDTO = getCustomerProfileInfoUseCase.execute(new CustomerInformationRequestDTO(customerId));
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/trips")
    public ResponseEntity<CustomerTripsResponseDTO> GetCustomerTrips(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            HttpServletRequest request
    ) {
        UUID customerId = UUID.fromString(request.getAttribute("user_id").toString());
        CustomerTripsResponseDTO responseDTO = getCustomerTrips.execute(new CustomerInformationRequestDTO(customerId), page, size);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/profile")
    public ResponseEntity<Any> editCustomer( HttpServletRequest request, @RequestBody EditCustomerResquestDTO resquestDTO) {
        UUID customerId = UUID.fromString(request.getAttribute("user_id").toString());
        editCustumerUseCase.execute(customerId, resquestDTO);
        return ResponseEntity.ok().build();
    }
}
