package github.lucasramallo.hopin.core.usecase.customer;

import github.lucasramallo.hopin.api.dtos.customer.EditCustomerResquestDTO;
import github.lucasramallo.hopin.core.domain.customer.Customer;
import github.lucasramallo.hopin.core.domain.customer.exceptions.CustomerNotFoundException;
import github.lucasramallo.hopin.core.domain.customer.util.CustomerValidations;
import github.lucasramallo.hopin.data.jpa.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class EditCustumerUseCase {
    @Autowired
    private CustomerRepository repository;

    public void execute(UUID id, EditCustomerResquestDTO requestDTO) {
        Optional<Customer> customer = repository.findById(id);

        if (customer.isEmpty()) {
            throw new CustomerNotFoundException("Customer not found!");
        }
        CustomerValidations.verifyEmailAlreadyRegistered(repository, customer.get(), requestDTO.email());
        CustomerValidations.validateName(requestDTO.name());
        CustomerValidations.validateEmial(requestDTO.email());

        customer.get().setName(requestDTO.name());
        customer.get().setEmail(requestDTO.email());

        repository.save(customer.get());
    }
}