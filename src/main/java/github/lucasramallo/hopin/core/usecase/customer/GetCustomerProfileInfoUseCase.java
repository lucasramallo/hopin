package github.lucasramallo.hopin.core.usecase.customer;

import github.lucasramallo.hopin.api.dtos.customer.CustomerInformationRequestDTO;
import github.lucasramallo.hopin.api.dtos.customer.CustomerInformationResponseDTO;
import github.lucasramallo.hopin.api.dtos.trip.TripDTOMapper;
import github.lucasramallo.hopin.api.dtos.trip.TripResponseDTO;
import github.lucasramallo.hopin.core.domain.customer.Customer;
import github.lucasramallo.hopin.core.domain.trips.Trip;
import github.lucasramallo.hopin.data.jpa.CustomerRepository;
import github.lucasramallo.hopin.data.jpa.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GetCustomerProfileInfoUseCase {
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

        List<TripResponseDTO> tripsResponseDTOs = trips.stream()
                .map(TripDTOMapper::tripToDto)
                .toList();

        return new CustomerInformationResponseDTO(
                customer.get().getId(),
                customer.get().getName(),
                customer.get().getEmail(),
                customer.get().getCreatedAt(),
                tripsResponseDTOs
        );
    }
}
