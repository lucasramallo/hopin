package github.lucasramallo.hopin.core.usecase.customer;

import github.lucasramallo.hopin.api.dtos.customer.CustomerInformationRequestDTO;
import github.lucasramallo.hopin.api.dtos.customer.CustomerInformationResponseDTO;
import github.lucasramallo.hopin.core.domain.customer.Customer;
import github.lucasramallo.hopin.core.domain.trips.Trip;
import github.lucasramallo.hopin.data.jpa.CustomerRepository;
import github.lucasramallo.hopin.data.jpa.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class GetCustomerProfileInfo {
    @Autowired
    private CustomerRepository repository;

    @Autowired
    private TripRepository tripRepository;

    public CustomerInformationResponseDTO execute(CustomerInformationRequestDTO request) {
        Optional<Customer> customer = repository.findById(request.id());
        ArrayList<Trip> trips = tripRepository.findAllByCustomerId(request.id());

        if(customer.isEmpty()) {
            throw new RuntimeException("User not found!");
        }

        return new CustomerInformationResponseDTO(
                customer.get().getId(),
                customer.get().getName(),
                customer.get().getEmail(),
                customer.get().getCreatedAt(),
                trips
        );
    }
}
