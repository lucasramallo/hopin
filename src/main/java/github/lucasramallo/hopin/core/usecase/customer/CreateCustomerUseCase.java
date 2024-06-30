package github.lucasramallo.hopin.core.usecase.customer;

import github.lucasramallo.hopin.api.dtos.customer.CreateCustomerRequestDTO;
import github.lucasramallo.hopin.core.domain.customer.Customer;
import github.lucasramallo.hopin.core.domain.customer.exceptions.EmailAlreadyRegisteredException;
import github.lucasramallo.hopin.core.domain.customer.util.CustomerValidations;
import github.lucasramallo.hopin.data.jpa.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CreateCustomerUseCase {
    @Autowired
    private CustomerRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Customer execute(CreateCustomerRequestDTO requestDTO) {
        CustomerValidations.verifyEmailAlreadyRegistered(repository, requestDTO.email());
        CustomerValidations.validateName(requestDTO.name());
        CustomerValidations.validateEmial(requestDTO.email());

        Customer newCustomer = new Customer();
        newCustomer.setId(UUID.randomUUID());
        newCustomer.setName(requestDTO.name());
        newCustomer.setEmail(requestDTO.email());

        String encodedPassword = passwordEncoder.encode(requestDTO.password());
        newCustomer.setPassword(encodedPassword);

        newCustomer.setCreatedAt(LocalDateTime.now());

        repository.save(newCustomer);

        return newCustomer;
    }
}
