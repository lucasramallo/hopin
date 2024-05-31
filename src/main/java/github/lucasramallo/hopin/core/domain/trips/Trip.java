package github.lucasramallo.hopin.core.domain.trips;

import github.lucasramallo.hopin.core.domain.customer.Customer;
import github.lucasramallo.hopin.core.domain.driver.Driver;
import github.lucasramallo.hopin.core.domain.payments.Payment;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "trips")
@Table(name = "trips")
@Data
public class Trip {
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
    @JoinColumn(name = "paymentID", nullable = false)
    private Payment payment;

    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private String source;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}