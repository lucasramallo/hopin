package github.lucasramallo.hopin.core.usecase.customer;

import github.lucasramallo.hopin.api.dtos.customer.CreateCustomerRequestDTO;
import github.lucasramallo.hopin.core.domain.customer.Customer;
import github.lucasramallo.hopin.data.jpa.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CreateCustomerUseCase {
    @Autowired
    private CustomerRepository repository;

    public Customer execute(CreateCustomerRequestDTO requestDTO) {
        Customer newCustomer = new Customer();

        newCustomer.setID(UUID.randomUUID());

        validateName(requestDTO.name());
        newCustomer.setName(requestDTO.name());

        validateEmial(requestDTO.email());
        newCustomer.setEmail(requestDTO.email());

        newCustomer.setPassword(requestDTO.password());
        newCustomer.setCreatedAt(LocalDateTime.now());

        repository.save(newCustomer);

        return newCustomer;
    }

    public void validateName(String name) {
        Pattern pattern = Pattern.compile("^[A-ZÀ-ÿ][A-Za-zÀ-ÿ ]{0,28}$");
        Matcher matcher = pattern.matcher(name);

        if (!matcher.matches()) {
            throw new RuntimeException("Invalid name!");
        }
    }

    public void validateEmial(String name) {
        Pattern pattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(name);

        if (!matcher.matches()) {
            throw new RuntimeException("Invalid email!");
        }
    }
}
