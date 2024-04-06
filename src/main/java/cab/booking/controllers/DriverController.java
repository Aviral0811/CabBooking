package cab.booking.controllers;


import cab.booking.dao.DriverRepository;
import cab.booking.dao.RideRepository;
import cab.booking.models.Driver;
import cab.booking.models.Ride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/driver")
public class DriverController {
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private RideRepository rideRepository;

    @PostMapping("/add")
    public Driver addDriver(@RequestBody Driver driver, @RequestBody Ride ride) {
        rideRepository.save(ride);
        driver.setRide(ride);
        return driverRepository.save(driver);
    }

}
