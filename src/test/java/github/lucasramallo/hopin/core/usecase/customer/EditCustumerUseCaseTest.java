package github.lucasramallo.hopin.core.usecase.customer;

import github.lucasramallo.hopin.api.dtos.customer.EditCustomerResquestDTO;
import github.lucasramallo.hopin.core.domain.customer.Customer;
import github.lucasramallo.hopin.core.domain.customer.exceptions.EmailAlreadyRegisteredException;
import github.lucasramallo.hopin.core.domain.customer.util.CustomerValidations;
import github.lucasramallo.hopin.core.globalExceptions.InvalidEmailException;
import github.lucasramallo.hopin.core.globalExceptions.InvalidUserNameException;
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

        assertThrows(EmailAlreadyRegisteredException.class, () -> CustomerValidations.verifyEmailAlreadyRegistered(repository, customer, requestDTO.email()));
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

    @Test
    @DisplayName("should throw an exception if the name or email is invalid.")
    void case04() {
        /*
           NAME:
            - Start with an uppercase letter (A-Z or À-ÿ).
            - Follow with uppercase or lowercase letters (A-Z, a-z) or accented characters (À-ÿ) and spaces.
            - Have a minimum length of 6 characters and a maximum of 51 characters.

           EMAIL:
            - Starts with a sequence that can have up to 64 characters, followed by "@".
            - The username before "@" can include uppercase and lowercase letters (A-Z, a-z), digits (0-9), and the special characters "_" and "-".
            - After "@", the email domain cannot start with "-" and must include uppercase and lowercase letters, digits, and the special character "-",
              followed by a "." and a sequence of lowercase letters (a-z) with at least two characters.
         */

        UUID id = UUID.randomUUID();
        Customer customer = new Customer(id, "Will Smith", "john@gmail.com", "senha123", LocalDateTime.now());

        EditCustomerResquestDTO InvalidDataDTO = new EditCustomerResquestDTO("Wil", "john");

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(customer));

        assertThrows(InvalidUserNameException.class, () -> CustomerValidations.validateName(InvalidDataDTO.name()));
        assertThrows(InvalidEmailException.class, () -> CustomerValidations.validateEmial(InvalidDataDTO.email()));
    }
}