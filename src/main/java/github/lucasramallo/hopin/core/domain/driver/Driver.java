package github.lucasramallo.hopin.core.domain.driver;

import github.lucasramallo.hopin.core.domain.cab.Cab;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "drivers")
@Table(name = "drivers")
@Data
public class Driver {
    @Id
    @Column
    private UUID id;

    @Column(nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "cabID")
    private Cab cab;

    @Column(nullable = false)
    private LocalDate DatedateOfBirth;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
