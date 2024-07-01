package github.lucasramallo.hopin.core.usecase.customer;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import github.lucasramallo.hopin.api.dtos.customer.CustomerAuthRequestDTO;
import github.lucasramallo.hopin.api.dtos.customer.CustomerAuthResponseDTO;
import github.lucasramallo.hopin.api.dtos.customer.CustomerResponseDTO;
import github.lucasramallo.hopin.api.security.JWTProvider;
import github.lucasramallo.hopin.core.domain.customer.Customer;
import github.lucasramallo.hopin.core.domain.customer.exceptions.CustomerNotFoundException;
import github.lucasramallo.hopin.core.globalExceptions.InvalidCredentialsException;
import github.lucasramallo.hopin.core.globalExceptions.RequiredFieldsException;
import github.lucasramallo.hopin.data.jpa.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class AuthCustomerUseCase {
    @Autowired
    private CustomerRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTProvider jwtProvider;

    public CustomerAuthResponseDTO execute(CustomerAuthRequestDTO customerAuthRequestDTO) {
        if(customerAuthRequestDTO.email() == null || customerAuthRequestDTO.password() == null) {
            throw new RequiredFieldsException("Email and password are required!");
        }

        Customer customer = repository.findByEmail(customerAuthRequestDTO.email()).orElseThrow(
               CustomerNotFoundException::new
        );

        var passwordMatches = passwordEncoder.matches(customerAuthRequestDTO.password(), customer.getPassword());

        if(!passwordMatches) {
            throw new InvalidCredentialsException();
        }

        String token = jwtProvider.generateToken(customer);

        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getCreatedAt()
        );
        return new CustomerAuthResponseDTO(token, customerResponseDTO);
    }
}
