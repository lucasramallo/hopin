package github.lucasramallo.hopin.data.jpa;

import github.lucasramallo.hopin.core.domain.driver.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DriverRepository extends JpaRepository<Driver, UUID> {
}
