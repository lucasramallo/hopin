package github.lucasramallo.hopin.core.usecase.driver;

import github.lucasramallo.hopin.api.dtos.driver.DriverInformationRequestDTO;
import github.lucasramallo.hopin.api.dtos.driver.DriverTripsResponseDTO;
import github.lucasramallo.hopin.api.dtos.trip.TripDTOMapper;
import github.lucasramallo.hopin.api.dtos.trip.TripResponseDTO;
import github.lucasramallo.hopin.core.domain.driver.Driver;
import github.lucasramallo.hopin.core.domain.driver.exceptions.DriverNotFoundException;
import github.lucasramallo.hopin.core.domain.trips.Trip;
import github.lucasramallo.hopin.data.jpa.DriverRepository;
import github.lucasramallo.hopin.data.jpa.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GetDriverTripsUseCase {
    @Autowired
    private DriverRepository repository;

    @Autowired
    private TripRepository tripRepository;

    public DriverTripsResponseDTO execute(DriverInformationRequestDTO requestDTO, int page, int size) {
        Optional<Driver> driver = repository.findById(requestDTO.id());

        if(driver.isEmpty()) {
            throw new DriverNotFoundException("Driver not found!");
        }

        ArrayList<Trip> trips = tripRepository.findAllByDriverId(driver.get().getId(), PageRequest.of(page, size));

        List<TripResponseDTO> tripsResponseDTOs = trips.stream()
                .map(TripDTOMapper::tripToDto)
                .collect(Collectors.toList());

        return new DriverTripsResponseDTO(
                tripsResponseDTOs
        );
    }
}
