package travel;

import java.util.List;
import activity.Activity;
import destination.Destination;
import passenger.Passenger;

public interface TravelPackage {
    String getName();
    int getPassengerCapacity();
    List<Destination> getItinerary();
    List<Passenger> getPassengers();
    void addDestination(Destination destination);
    void addPassenger(Passenger passenger);
    Passenger findPassengerByNumber(int passengerNumber);
    boolean signUpPassengerForActivity(Passenger passenger,Activity activity);
}
