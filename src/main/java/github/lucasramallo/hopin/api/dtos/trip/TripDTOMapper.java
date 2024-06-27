package github.lucasramallo.hopin.api.dtos.trip;

import github.lucasramallo.hopin.core.domain.customer.Customer;
import github.lucasramallo.hopin.core.domain.driver.Driver;
import github.lucasramallo.hopin.core.domain.trips.Trip;

public class TripDTOMapper {
    /**
     *
     * @param trip
     * Returns a trip DTO containing only the information that should be returned.
     */
    public static TripResponseDTO tripToDto(Trip trip) {
        return new TripResponseDTO(
                trip.getId(),
                CustomertoDto(trip.getCustomer()),
                DrivertoDto(trip.getDriver()),
                trip.getPayment(),
                trip.getStatus(),
                trip.getSource(),
                trip.getDestination(),
                trip.getCreatedAt()
        );
    }

    private static TripCustomerResponseDTO CustomertoDto(Customer customer) {
        return new TripCustomerResponseDTO(
                customer.getId(),
                customer.getName(),
                customer.getEmail()
        );
    }

    private static TripDriverResponseDTO DrivertoDto(Driver driver) {
        return new TripDriverResponseDTO(
                driver.getId(),
                driver.getName(),
                driver.getCab()
        );
    }
}
