package github.lucasramallo.hopin.core.domain.rating;

import github.lucasramallo.hopin.core.domain.customer.Customer;
import github.lucasramallo.hopin.core.domain.driver.Driver;
import github.lucasramallo.hopin.core.domain.trips.Trip;
import jakarta.persistence.*;

import java.util.UUID;

public class Rating {
    @Id
    @Column
    private UUID id;

    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "customerID", nullable = false)
    private Customer customer;

    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "driverID", nullable = false)
    private Driver driver;

    @Column(nullable = false)
    @OneToOne
    @JoinColumn(name = "tripID", nullable = false)
    private Trip trip;
}
