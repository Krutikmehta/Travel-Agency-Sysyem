package activity;

import passenger.Passenger;

import java.util.ArrayList;
import java.util.List;

public class ActivityImpl implements Activity {
    private String name;
    private String description;
    private double cost;
    private int capacity;
    private List<Integer> passengerList;

    public ActivityImpl(String name, String description, double cost, int capacity) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.capacity = capacity;
        passengerList = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean isAvailable() {
        return capacity > passengerList.size();
    }

    @Override
    public boolean signUpPassenger(Passenger passenger) {
        if(passengerList.contains(passenger.getPassengerNumber())){
            return false;
        }
        if (isAvailable() && passenger.signUpForActivity(cost)) {
            passengerList.add(passenger.getPassengerNumber());
            return true;
        }
        return false;
    }

    @Override
    public List<Integer> getPassengerList() {
        return passengerList;
    }

}
