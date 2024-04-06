package cab.booking;

import cab.booking.models.Location;

public class Util {
    public static double calculateDistance(Location loc1, Location loc2) {
        return Math.sqrt(Math.pow(loc2.getX() - loc1.getX(), 2) + Math.pow(loc2.getY() - loc1.getY(), 2));
    }
}
