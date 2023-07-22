package utils;

import activity.Activity;
import destination.Destination;
import passenger.Passenger;
import passenger.PremiumPassenger;
import travel.TravelPackage;

public  class PrintUtilsImpl implements PrintUtils {

    @Override
    public  void printItinery(TravelPackage travelPackage) {
        System.out.println("---------- Printing itinery for package: " + travelPackage.getName() + " ----------\n");
        for(Destination destination: travelPackage.getItinerary()){
            System.out.println("----- Destination: " + destination.getName() + " -----");
            for (Activity activity:destination.getActivities()){
                System.out.println("----- Activities -----");
                System.out.println("name:  "+ activity.getName());
                System.out.println("description:  "+ activity.getDescription());
                System.out.println("cost:  "+ activity.getCost());
                System.out.println("capacity:  "+ activity.getCapacity() + "\n");
            }
        }
    }

    @Override
    public  void printPassengers(TravelPackage travelPackage) {
        System.out.println("---------- Printing passengers for package: " + travelPackage.getName() + " ----------");
        System.out.println("Total passenger capacity: " + travelPackage.getPassengerCapacity());
        System.out.println("# of passengers enrolled: " + travelPackage.getPassengers().size() + "\n");

        System.out.println("----- Passengers  -----");
        for(Passenger passenger: travelPackage.getPassengers()){
            System.out.println("name:  "+ passenger.getName());
            System.out.println("passenger number:  "+ passenger.getPassengerNumber() + "\n");
        }
    }

    @Override
    public  void printPassengerDetails(TravelPackage travelPackage,Integer passengerNumber) {
        System.out.println("---------- Passenger details for passenger number: " + passengerNumber + " ----------");

        Passenger passenger = null;
        for(Passenger pass : travelPackage.getPassengers()){
            if(pass.getPassengerNumber() == passengerNumber){
                passenger = pass;
            }
        }
        if(passenger == null){
            System.out.println("passenger not found");
            return;
        }
        System.out.println("name: " + passenger.getName());
        System.out.println("number: " + passenger.getPassengerNumber() + "\n");
        if(!(passenger instanceof PremiumPassenger)){
            System.out.println("balance: " + passenger.getBalance() + "\n");
        }

        System.out.println("----- Activities signed up for -----");
        for(Destination destination: travelPackage.getItinerary()){
            for(Activity activity:destination.getActivities()){
                if(activity.getPassengerList().contains(passengerNumber)){
                    System.out.println("name :"+ activity.getName());
                    System.out.println("destination :"+ destination.getName());
                    System.out.println("cost :"+ activity.getCost()+"\n");
                }
            }
        }
    }

    @Override
    public  void printUnfilledActivites(TravelPackage travelPackage) {

        System.out.println("---------- Activities with space remaining for packages: "+ travelPackage.getName()+ "----------\n");
        for(Destination destination: travelPackage.getItinerary()){
            for(Activity activity:destination.getActivities()){
                if(activity.isAvailable()){
                    System.out.println("activity name :"+ activity.getName());
                    System.out.println("seats remaining :"+ (activity.getCapacity() - activity.getPassengerList().size())+"\n");
                }
            }
        }
    }
}
