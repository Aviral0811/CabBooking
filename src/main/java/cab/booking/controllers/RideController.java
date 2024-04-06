package cab.booking.controllers;

import cab.booking.dao.DriverRepository;
import cab.booking.exceptions.CabNotFound;
import cab.booking.models.Driver;
import cab.booking.models.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ride")
public class RideController {

    @Autowired
    private DriverRepository driverRepository;

    @GetMapping("/findRide")
    public ResponseEntity<List<Driver>> findRide(@RequestParam String username, @RequestParam double sourceX, @RequestParam double sourceY,
                                          @RequestParam double destinationX, @RequestParam double destinationY) {
        Location source = new Location(sourceX, sourceY);
        Location destination = new Location(destinationX, destinationY);
        List<Driver> availableDrivers = driverRepository.findByCurrentLocation(source, destination, 5);

        if (availableDrivers.isEmpty()) {
            return ResponseEntity.notFound().build(); // No available drivers found
        } else {
            return ResponseEntity.ok(availableDrivers); // Return the list of available drivers
        }
    }

    @PutMapping("/selectRide")
    public ResponseEntity<String> selectRide(@RequestParam String username, @RequestParam String driverName) {
        try {
            Driver chosenDriver = driverRepository.findByName(driverName);
            if (chosenDriver != null && chosenDriver.isAvailable()) {
                chosenDriver.setAvailable(false);
                driverRepository.save(chosenDriver);
                return ResponseEntity.ok("Ride booked with driver: " + chosenDriver.getName());
            } else {
                throw new CabNotFound("Driver " + driverName + " not found or not available.");
            }
        } catch (CabNotFound e) {
            return ResponseEntity.notFound().build();
        }
    }
}