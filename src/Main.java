import activity.*;
import destination.*;
import passenger.*;
import travel.*;
import utils.PrintUtils;
import utils.PrintUtilsImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<TravelPackage> travelPackages;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        travelPackages = new ArrayList<>();
        PrintUtils printUtils = new PrintUtilsImpl();
        while(true){
            System.out.println("------ options ------");
            System.out.println("enter 0 to create a travel package");
            System.out.println("enter 1 to get travel package itinery");
            System.out.println("enter 2 to get travel package passengers");
            System.out.println("enter 3 to get travel package passenger details");
            System.out.println("enter 4 to get travel package unfilled activities");

            int optionSelected = scanner.nextInt();
            scanner.nextLine();
            if(optionSelected == 0) {
                travelPackageFlow(scanner, printUtils);
            }
            else{
                if(travelPackages.size() == 0){
                    System.out.println("No travel packages created. Create one first\n");
                    continue;
                }
                System.out.println("Enter travel package name");
                System.out.println("Availabe Packages - ");
                for(TravelPackage travelPackage:travelPackages){
                    System.out.println("pacakge name: " + travelPackage.getName());
                }
                String packageName = scanner.nextLine().toLowerCase();
                TravelPackage selectedTravelPackage = null;
                for(TravelPackage travelPackage:travelPackages){
                    if(travelPackage.getName().toLowerCase().equals(packageName)){
                        selectedTravelPackage = travelPackage;
                    }
                }
                if(selectedTravelPackage == null){
                    System.out.println("Travel package with the given name not found\n");
                }else{
                    handlePrinting(selectedTravelPackage,optionSelected,printUtils,scanner);
                }

            }


        }
    }

    private static void handlePrinting(TravelPackage travelPackage,int option,PrintUtils printUtils,Scanner scanner){
        switch (option){
            case 1:
                printUtils.printItinery(travelPackage);
                break;
            case 2:
                printUtils.printPassengers(travelPackage);
                break;
            case 3:
                System.out.println("Enter passenger number: ");
                int passengerNumber = scanner.nextInt();
                scanner.nextLine();
                printUtils.printPassengerDetails(travelPackage,passengerNumber);
                break;
            case 4:
                printUtils.printUnfilledActivites(travelPackage);
                break;

        }
    }
    private static void travelPackageFlow(Scanner scanner, PrintUtils printUtils){
        TravelPackage travelPackage = createTravelPackage(scanner);
        travelPackages.add(travelPackage);

        System.out.println("Travel Package created: " + travelPackage.getName());
        System.out.println("# of travel packages: " + travelPackages.size());

        addDestinations(scanner, travelPackage);
        addActivities(scanner, travelPackage);
        addPassengers(scanner, travelPackage);
        signUpPassengersForActivities(scanner, travelPackage);

        //print the package details
        printUtils.printItinery(travelPackage);
        printUtils.printPassengers(travelPackage);
        printUtils.printPassengerDetails(travelPackage,1);
        printUtils.printUnfilledActivites(travelPackage);
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
        System.out.println("\n Adding Destinations to the Travel Package (Type 'done' when finished):");
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
        System.out.println("\n Adding Activities to Destinations (Type 'done' when finished):");
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
        System.out.println("\n Adding Passengers to the Travel Package (Type 'done' when finished):");
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
            String passengerType = scanner.nextLine().toLowerCase();

            int balance = 0;
            if(!passengerType.equals("premium")){
                System.out.print("Enter the passenger balance amount ");
                balance= scanner.nextInt();
                scanner.nextLine(); // Consume the remaining newline

            }
            Passenger passenger;
            switch (passengerType) {
                case "standard":
                    passenger = new StandardPassenger(passengerName, passengerNumber, balance);
                    break;
                case "gold":
                    passenger = new GoldPassenger(passengerName, passengerNumber, balance);
                    break;
                case "premium":
                    passenger = new PremiumPassenger(passengerName, passengerNumber,balance);
                    break;
                default:
                    System.out.println("Invalid passenger type. Using Standard Passenger by default.");
                    passenger = new StandardPassenger(passengerName, passengerNumber, balance);
            }

            boolean isPassengerAdded = travelPackage.addPassenger(passenger);
            if(!isPassengerAdded){
                System.out.println("Passenger limit exceeded for this package or same id present");
                return;
            }
            System.out.println("Passenger added");
        }
    }

    private static void signUpPassengersForActivities(Scanner scanner, TravelPackage travelPackage) {
        System.out.println("\n Signing up Passengers for Activities (Type 'done' when finished):");
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

            System.out.println("Activities available");
            Passenger passenger = travelPackage.findPassengerByNumber(passengerNumber);
            if (passenger == null) {
                System.out.println("Passenger not found.");
                continue;
            }

            for (Destination destination : travelPackage.getItinerary()) {
                System.out.println("Destination: " + destination.getName());
                if(destination.getActivities().size() == 0){
                    System.out.println("Activitites: NONE");
                    continue;
                }
                System.out.println("Activities:");
                for (Activity activity:destination.getActivities()){
                    System.out.println("name:  "+ activity.getName());
                    System.out.println("description:  "+ activity.getDescription());
                    System.out.println("cost:  "+ activity.getCost());
                    System.out.println("capacity:  "+ activity.getCapacity());
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
                    System.out.println("Failed to sign up. Insufficient balance or activity capacity reached or already registered.");
                }
            }
        }
    }
}

