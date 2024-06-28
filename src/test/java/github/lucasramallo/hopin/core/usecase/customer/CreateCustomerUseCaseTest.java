package github.lucasramallo.hopin.core.usecase.customer;

import github.lucasramallo.hopin.api.dtos.customer.CreateCustomerRequestDTO;
import github.lucasramallo.hopin.api.dtos.customer.EditCustomerResquestDTO;
import github.lucasramallo.hopin.core.domain.customer.Customer;
import github.lucasramallo.hopin.data.jpa.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CreateCustomerUseCaseTest {
    @Mock
    private CustomerRepository repository;

    @Autowired
    @InjectMocks
    private CreateCustomerUseCase createCustomerUseCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create a new Customer")
    void case01() {
        CreateCustomerRequestDTO requestDTO = new CreateCustomerRequestDTO("John Doe", "john@gmail.com", "senha123");
        Customer customer = createCustomerUseCase.execute(requestDTO);

        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(repository, Mockito.times(1)).findByEmail(Mockito.any());
        assertNotNull(customer);

        assertEquals(customer.getName(), requestDTO.name());
        assertEquals(customer.getEmail(), requestDTO.email());
    }

    @Test
    @DisplayName("Should throw an exception when the customer name is invalid")
    void case02() {
        CreateCustomerRequestDTO requestDTO = new CreateCustomerRequestDTO("invalidname", "john@gmail.com", "senha123");

        assertThrows(RuntimeException.class, () -> createCustomerUseCase.execute(requestDTO));
    }

    @Test
    @DisplayName("Should throw an exception when the customer email is invalid")
    void case03() {
        ArrayList<String> invalidEmails = new ArrayList<>(List.of("username.@domain.com", ".user.name@domain.com", "user-name@domain.com.", "username@.com"));

        invalidEmails.forEach(invalidEmail -> {
            CreateCustomerRequestDTO requestDTO = new CreateCustomerRequestDTO("John Doe", invalidEmail, "senha123");

            assertThrows(RuntimeException.class, () -> createCustomerUseCase.execute(requestDTO));
        });
    }

    @Test
    @DisplayName("Should throw an exception when the customer email already exists")
    void case04() {
        Customer existingCustomer =  new Customer(UUID.randomUUID(), "John Willians", "john@gmail.com", "senha123", LocalDateTime.now());
        Mockito.when(repository.findByEmail("john@gmail.com")).thenReturn(Optional.of(existingCustomer));

        CreateCustomerRequestDTO requestDTO = new CreateCustomerRequestDTO("John Doe", "john@gmail.com", "senha123");

        assertThrows(RuntimeException.class, () -> createCustomerUseCase.execute(requestDTO));
    }
}