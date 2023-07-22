package activity;

import passenger.Passenger;

import java.util.List;

public interface Activity {
    String getName();
    String getDescription();
    double getCost();
    int getCapacity();
    boolean isAvailable();
    boolean signUpPassenger(Passenger passenger);
    List<Integer> getPassengerList();
}

