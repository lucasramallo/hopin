package github.lucasramallo.hopin.core.usecase.customer;

import github.lucasramallo.hopin.api.dtos.customer.CustomerInformationRequestDTO;
import github.lucasramallo.hopin.core.domain.customer.Customer;
import github.lucasramallo.hopin.data.jpa.CustomerRepository;
import github.lucasramallo.hopin.data.jpa.TripRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GetCustomerProfileInfoUseCaseTest {
    @Mock
    private CustomerRepository repository;

    @Mock
    private TripRepository tripRepository;

    @Autowired
    @InjectMocks
    private GetCustomerProfileInfoUseCase getCustomerProfileInfoUseCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return a CustomerInformationResponseDTO with the correct data")
    void case01() {
        UUID id = UUID.randomUUID();
        Customer customer = new Customer(id, "Will Smith", "john@gmail.com", "senha123", LocalDateTime.now());
        CustomerInformationRequestDTO customerInformationRequestDTO = new CustomerInformationRequestDTO(id);

        // To be continued
    }
}