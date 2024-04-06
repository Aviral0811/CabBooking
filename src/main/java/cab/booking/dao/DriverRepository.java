package cab.booking.dao;

import cab.booking.models.Driver;
import cab.booking.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    Driver findByName(String name);
    List<Driver> findByCurrentLocation(Location source, Location destination, double maxDistance);
}

