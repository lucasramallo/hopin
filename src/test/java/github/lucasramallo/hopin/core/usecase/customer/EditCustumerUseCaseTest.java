package github.lucasramallo.hopin.core.usecase.customer;

import github.lucasramallo.hopin.api.dtos.customer.EditCustomerResquestDTO;
import github.lucasramallo.hopin.core.domain.customer.Customer;
import github.lucasramallo.hopin.core.domain.customer.util.CustomerValidations;
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
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EditCustumerUseCaseTest {
    @Mock
    private CustomerRepository repository;

    @Autowired
    @InjectMocks
    private EditCustumerUseCase editCustumerUseCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should change the data and persist")
    void case01() {
        UUID id = UUID.randomUUID();
        Customer customer = new Customer(id, "John Doe", "john@gmail.com", "senha123", LocalDateTime.now());
        EditCustomerResquestDTO requestDTO = new EditCustomerResquestDTO("John Doe Silva", "john2@gmail.com");

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(customer));

        editCustumerUseCase.execute(id, requestDTO);

        Mockito.verify(repository, Mockito.times(1)).findByEmail(Mockito.any());
        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any());

        assertEquals(customer.getName(), requestDTO.name());
        assertEquals(customer.getEmail(), requestDTO.email());
    }

    @Test
    @DisplayName("Should throw an exception if the new email is already registered by another user")
    void case02() {
        UUID id = UUID.randomUUID();
        Customer customer = new Customer(id, "Will Smith", "john@gmail.com", "senha123", LocalDateTime.now());
        Customer customerWithEmailAlreadyRegistered = new Customer(UUID.randomUUID(), "John Doe", "john2@gmail.com", "senha123", LocalDateTime.now());
        EditCustomerResquestDTO requestDTO = new EditCustomerResquestDTO("Will Smith", "john2@gmail.com");

        Mockito.when(repository.findByEmail("john2@gmail.com")).thenReturn(Optional.of(customerWithEmailAlreadyRegistered));

        assertThrows(RuntimeException.class, () -> CustomerValidations.verifyEmailAlreadyRegistered(repository, customer, requestDTO.email()));
    }

    @Test
    @DisplayName("Should not throw an exception if the email provided is the same already registered by the customer.")
    void case03() {
        UUID id = UUID.randomUUID();
        Customer customer = new Customer(id, "Will Smith", "john@gmail.com", "senha123", LocalDateTime.now());
        EditCustomerResquestDTO requestDTO = new EditCustomerResquestDTO("Will Smith Silva", "john@gmail.com");

        Mockito.when(repository.findByEmail("john2@gmail.com")).thenReturn(Optional.of(customer));

        assertDoesNotThrow(() -> CustomerValidations.verifyEmailAlreadyRegistered(repository, customer, requestDTO.email()));
    }

    /**
     * EDIT
     * - deve lançar exceção caso o name ou o email sejam inválidos
     */
}