package github.lucasramallo.hopin.data.jpa;

import github.lucasramallo.hopin.core.domain.cab.Cab;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CabRepository extends JpaRepository<Cab, UUID> {
}
