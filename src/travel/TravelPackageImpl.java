package travel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import activity.Activity;
import destination.Destination;
import passenger.Passenger;
import passenger.PremiumPassenger;

public class TravelPackageImpl implements TravelPackage {
    private String name;
    private int passengerCapacity;
    private List<Destination> itinerary;
    private List<Passenger> passengers;
    private Set<String> destinationNames;

    public TravelPackageImpl(String name, int passengerCapacity) {
        this.name = name;
        this.passengerCapacity = passengerCapacity;
        this.itinerary = new ArrayList<>();
        this.passengers = new ArrayList<>();
        this.destinationNames = new HashSet<>();
    }

    public TravelPackageImpl(String name, int passengerCapacity,List<Destination> itinerary) {
        this.name = name;
        this.passengerCapacity = passengerCapacity;
        this.passengers = new ArrayList<>();
        this.destinationNames = new HashSet<>();
        this.itinerary = new ArrayList<>();
        if (itinerary != null) {
            for (Destination destination : itinerary) {
                addDestination(destination);
            }
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    @Override
    public List<Destination> getItinerary() {
        return itinerary;
    }

    @Override
    public List<Passenger> getPassengers() {
        return passengers;
    }

    @Override
    public void addDestination(Destination destination) {
        // Check if the destination name already exists in the set before adding
        if (!destinationNames.contains(destination.getName())) {
            itinerary.add(destination);
            destinationNames.add(destination.getName());
        }
    }

    @Override
    public boolean addPassenger(Passenger passenger) {
        if (passengers.size() < passengerCapacity) {
            passengers.add(passenger);
            return true;
        }
        return false;
    }

    @Override
    public Passenger findPassengerByNumber(int passengerNumber) {
        for (Passenger passenger : passengers) {
            if (passenger.getPassengerNumber() == passengerNumber) {
                return passenger;
            }
        }
        return null;
    }

    @Override
    public boolean signUpPassengerForActivity(Passenger passenger, Activity activity) {
        if (passenger != null && activity != null){
            // check if the passenger has opted for this travel package
            if(findPassengerByNumber(passenger.getPassengerNumber()) != null){
                    return activity.signUpPassenger(passenger);
            }
        }

        return false;
    }


}
