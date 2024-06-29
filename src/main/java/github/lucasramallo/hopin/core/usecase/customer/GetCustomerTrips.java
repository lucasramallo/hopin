package github.lucasramallo.hopin.core.usecase.customer;

import github.lucasramallo.hopin.api.dtos.customer.CustomerInformationRequestDTO;
import github.lucasramallo.hopin.api.dtos.customer.CustomerInformationResponseDTO;
import github.lucasramallo.hopin.api.dtos.customer.CustomerTripsResponseDTO;
import github.lucasramallo.hopin.api.dtos.trip.TripDTOMapper;
import github.lucasramallo.hopin.api.dtos.trip.TripResponseDTO;
import github.lucasramallo.hopin.core.domain.customer.Customer;
import github.lucasramallo.hopin.core.domain.customer.exceptions.CustomerNotFoundException;
import github.lucasramallo.hopin.core.domain.trips.Trip;
import github.lucasramallo.hopin.data.jpa.CustomerRepository;
import github.lucasramallo.hopin.data.jpa.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetCustomerTrips {
    @Autowired
    private CustomerRepository repository;

    @Autowired
    private TripRepository tripRepository;

    public CustomerTripsResponseDTO execute(CustomerInformationRequestDTO request, int page, int size) {
        Optional<Customer> customer = repository.findById(request.id());

        if(customer.isEmpty()) {
            throw new CustomerNotFoundException();
        }

        List<Trip> trips = tripRepository.findAllByCustomerId(customer.get().getId(), PageRequest.of(page, size));

        List<TripResponseDTO> tripsResponseDTOs = trips.stream()
                .map(TripDTOMapper::tripToDto)
                .toList();

        return new CustomerTripsResponseDTO(
                tripsResponseDTOs
        );
    }
}
