package activity;

import passenger.Passenger;

public interface Activity {
    String getName();
    String getDescription();
    double getCost();
    int getCapacity();
    boolean isAvailable();
    boolean signUpPassenger(Passenger passenger);
    void setCapacity(int capacity);
}

