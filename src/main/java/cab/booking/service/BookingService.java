package cab.booking.service;

import cab.booking.dao.DriverRepository;
import cab.booking.dao.RideRepository;
import cab.booking.dao.UserRepository;
import cab.booking.exceptions.CabNotFound;
import cab.booking.models.Driver;
import cab.booking.models.Location;
import cab.booking.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private RideRepository rideRepository;

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<Driver> findRide(Location source, Location destination) {
        return driverRepository.findByCurrentLocationAndDestinationLocationNear(source, destination, 5);
    }

    public ResponseEntity<String> chooseRide(String driverName) throws CabNotFound {
        Driver chosenDriver = driverRepository.findByName(driverName);
        if (chosenDriver != null && chosenDriver.isAvailable()) {
            chosenDriver.setAvailable(false);
            driverRepository.save(chosenDriver);
            return ResponseEntity.ok("Ride booked with driver: " + chosenDriver.getName());
        } else {
            throw new CabNotFound("Driver " + driverName + " not found or not available.");
        }
    }

    public Driver addDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public Driver updateDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public Driver findDriverByName(String name) {
        return driverRepository.findByName(name);
    }

    public List<Driver> findDriverByLocation(Location source, Location destination, double distance) {
        return driverRepository.findByCurrentLocationAndDestinationLocationNear(source, destination, distance);
    }
}