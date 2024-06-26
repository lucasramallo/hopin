package github.lucasramallo.hopin.core.domain.rating;

import github.lucasramallo.hopin.core.domain.customer.Customer;
import github.lucasramallo.hopin.core.domain.driver.Driver;
import github.lucasramallo.hopin.core.domain.trips.Trip;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity(name = "ratings")
@Table(name = "ratings")
@Data
public class Rating {
    @Id
    @Column
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @OneToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;
}
