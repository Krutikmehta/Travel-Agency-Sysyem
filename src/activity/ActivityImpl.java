package activity;

import passenger.Passenger;

public class ActivityImpl implements Activity {
    private String name;
    private String description;
    private double cost;
    private int capacity;

    public ActivityImpl(String name, String description, double cost, int capacity) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.capacity = capacity;
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

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean isAvailable() {
        return capacity > 0;
    }

    @Override
    public boolean signUpPassenger(Passenger passenger) {
        if (isAvailable() && passenger.signUpForActivity(cost)) {
            setCapacity((capacity - 1));
            return true;
        }
        return false;
    }
}
