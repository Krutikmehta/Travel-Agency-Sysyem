import activity.*;
import destination.*;
import passenger.*;
import travel.*;

import java.util.ArrayList;
import java.util.List;
//
//public class Main {
//    public static void main(String[] args) {
//        // Create activities
//        Activity activity1 = new ActivityImpl("Activity1", "", 50,3);
//        Activity activity2 = new ActivityImpl("Activity2", "",100, 5);
//
//        // Create destinations and add activities
//        Destination destination1 = new DestinationImpl("Destination1");
//        destination1.getActivities().add(activity1);
//        destination1.getActivities().add(activity2);
//
//        // Create travel package and add destinations
//        List<Destination> itinerary = new ArrayList<>();
//        itinerary.add(destination1);
//        TravelPackage package1 = new TravelPackageImpl("Package1", 10, itinerary);
//
//        // Create passengers
//        Passenger standardPassenger = new StandardPassenger("Standard Passenger", 1, 200);
//        Passenger goldPassenger = new GoldPassenger("Gold Passenger", 2, 300);
//        Passenger premiumPassenger = new PremiumPassenger("Premium Passenger", 3);
//
//        // Add passengers to the travel package
//        package1.addPassenger(standardPassenger);
//        package1.addPassenger(goldPassenger);
//        package1.addPassenger(premiumPassenger);
//
//        // Test signing up passengers for activities
//        System.out.println(activity1.signUpPassenger(standardPassenger)); // Should return true
//        System.out.println(activity2.signUpPassenger(goldPassenger));     // Should return true
//        System.out.println(activity1.signUpPassenger(premiumPassenger));  // Should return true
//        System.out.println(activity2.signUpPassenger(standardPassenger)); // Should return false (insufficient balance)
//        System.out.println(activity1.isAvailable());                      // Should be false (activity1 capacity exhausted)
//        System.out.println(activity2.isAvailable());                      // Should be true (activity2 still available)
//    }
//}

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TravelPackage travelPackage = createTravelPackage(scanner);

        System.out.println("Travel Package created: " + travelPackage.getName());
        addDestinations(scanner, travelPackage);
        addActivities(scanner, travelPackage);
        addPassengers(scanner, travelPackage);
        signUpPassengersForActivities(scanner, travelPackage);
    }

    private static TravelPackage createTravelPackage(Scanner scanner) {
        System.out.print("Enter the name of the Travel Package: ");
        String packageName = scanner.nextLine();

        System.out.print("Enter the passenger capacity of the Travel Package: ");
        int passengerCapacity = scanner.nextInt();
        scanner.nextLine(); // Consume the remaining newline

        return new TravelPackageImpl(packageName, passengerCapacity);
    }

    private static void addDestinations(Scanner scanner, TravelPackage travelPackage) {
        System.out.println("Adding Destinations to the Travel Package (Type 'done' when finished):");
        while (true) {
            System.out.print("Enter the name of the Destination or 'done': ");
            String destinationName = scanner.nextLine();
            if (destinationName.equalsIgnoreCase("done")) {
                break;
            }
            Destination destination = new DestinationImpl(destinationName);
            travelPackage.addDestination(destination);
        }
    }

    private static void addActivities(Scanner scanner, TravelPackage travelPackage) {
        System.out.println("Adding Activities to Destinations (Type 'done' when finished):");
        for (Destination destination : travelPackage.getItinerary()) {
            System.out.println("Destination: " + destination.getName());
            while (true) {
                System.out.print("Enter the name of the Activity or 'done': ");
                String activityName = scanner.nextLine();
                if (activityName.equalsIgnoreCase("done")) {
                    break;
                }

                System.out.print("Enter the description of the Activity: ");
                String activityDescription = scanner.nextLine();

                System.out.print("Enter the cost of the Activity: ");
                double activityCost = scanner.nextDouble();

                System.out.print("Enter the capacity of the Activity: ");
                int activityCapacity = scanner.nextInt();
                scanner.nextLine(); // Consume the remaining newline

                Activity activity = new ActivityImpl(activityName, activityDescription, activityCost, activityCapacity);
                destination.addActivity(activity);
            }
        }
    }

    private static void addPassengers(Scanner scanner, TravelPackage travelPackage) {
        System.out.println("Adding Passengers to the Travel Package (Type 'done' when finished):");
        while (true) {
            System.out.print("Enter the name of the Passenger or 'done': ");
            String passengerName = scanner.nextLine();
            if (passengerName.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter the passenger number: ");
            int passengerNumber = scanner.nextInt();
            scanner.nextLine(); // Consume the remaining newline

            System.out.print("Enter the passenger type (Standard, Gold, Premium): ");
            String passengerType = scanner.nextLine();

            Passenger passenger;
            switch (passengerType.toLowerCase()) {
                case "standard":
                    passenger = new StandardPassenger(passengerName, passengerNumber, 1000);
                    break;
                case "gold":
                    passenger = new GoldPassenger(passengerName, passengerNumber, 2000);
                    break;
                case "premium":
                    passenger = new PremiumPassenger(passengerName, passengerNumber,0);
                    break;
                default:
                    System.out.println("Invalid passenger type. Using Standard Passenger by default.");
                    passenger = new StandardPassenger(passengerName, passengerNumber, 1000);
            }

            travelPackage.addPassenger(passenger);
        }
    }

    private static void signUpPassengersForActivities(Scanner scanner, TravelPackage travelPackage) {
        System.out.println("Signing up Passengers for Activities (Type 'done' when finished):");
        while (true) {
            System.out.print("Enter the passenger number or 'done': ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("done")) {
                break;
            }

            int passengerNumber;
            try {
                passengerNumber = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid passenger number. Please try again.");
                continue;
            }

            System.out.println("Passenger " + passengerNumber + " Activities:");
            Passenger passenger = travelPackage.findPassengerByNumber(passengerNumber);
            if (passenger == null) {
                System.out.println("Passenger not found.");
                continue;
            }

            for (Destination destination : travelPackage.getItinerary()) {
                System.out.println("Destination: " + destination.getName());
                System.out.println("Activities:");
                for (Activity activity : destination.getActivities()) {
                    System.out.println(activity.getName() + " - " + activity.getDescription() + " - Cost: " + activity.getCost());
                }

                System.out.print("Enter the name of the Activity to sign up or 'skip': ");
                String activityName = scanner.nextLine();
                if (activityName.equalsIgnoreCase("skip")) {
                    continue;
                }

                Activity selectedActivity = destination.findActivityByName(activityName);
                if (selectedActivity == null) {
                    System.out.println("Activity not found.");
                    continue;
                }

                boolean signedUp = travelPackage.signUpPassengerForActivity(passenger,selectedActivity);
                if (signedUp) {
                    System.out.println("Passenger successfully signed up for " + selectedActivity.getName());
                } else {
                    System.out.println("Failed to sign up. Insufficient balance or activity capacity reached.");
                }
            }
        }
    }
}