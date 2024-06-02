package github.lucasramallo.hopin.core.domain.cab;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Entity(name = "cabs")
@Table(name = "cabs")
@Data
public class Cab {
    @Id
    @Column
    private UUID id;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String plateNum;
}