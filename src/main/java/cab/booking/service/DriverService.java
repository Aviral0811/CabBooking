package cab.booking.service;

import cab.booking.dao.DriverRepository;
import cab.booking.models.Driver;
import cab.booking.models.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {
    DriverRepository driverRepository;
    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }
    public Driver addDriver(Driver driver){
        return driverRepository.save(driver);
    }
    public List<Driver> findDriverByLocation(Location source, Location destination, double distance){
        return driverRepository.findByCurrentLocation(source, destination, distance);
    }
    public Driver findDriverByName(String name){
        return driverRepository.findByName(name);
    }
    public Driver updateDriver(Driver driver){
        return driverRepository.save(driver);
    }
}
