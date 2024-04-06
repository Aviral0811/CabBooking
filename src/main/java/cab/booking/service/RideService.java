package cab.booking.service;

import cab.booking.dao.DriverRepository;
import cab.booking.dao.RideRepository;
import cab.booking.models.Driver;
import cab.booking.models.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService {
    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private DriverService driverService;

    @Autowired
    DriverRepository driverRepository;

    public List<Driver> findRide(Location source, Location destination) {
        return driverRepository.findByCurrentLocation(source, destination, 5);
    }

    public ResponseEntity<String> chooseRide(String driverName) {
        Driver chosenDriver = driverRepository.findByName(driverName);
        if (chosenDriver != null && chosenDriver.isAvailable()) {
            chosenDriver.setAvailable(false);
            driverRepository.save(chosenDriver);
            return ResponseEntity.ok("Ride booked with driver: " + chosenDriver.getName());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
