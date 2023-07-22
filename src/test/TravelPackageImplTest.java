package test;

import static org.junit.jupiter.api.Assertions.*;

import activity.ActivityImpl;
import destination.DestinationImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import activity.Activity;
import destination.Destination;
import passenger.GoldPassenger;
import passenger.PremiumPassenger;
import passenger.StandardPassenger;
import passenger.Passenger;
import travel.TravelPackageImpl;

import java.util.ArrayList;
import java.util.List;

public class TravelPackageImplTest {
    private TravelPackageImpl travelPackage;
    private List<Destination> itinerary;
    private Destination destination1;
    private Destination destination2;
    private Activity activity1;
    private Activity activity2;

    @BeforeEach
    public void setUp() {
        itinerary = new ArrayList<>();
        destination1 = new DestinationImpl("Destination 1");
        destination2 = new DestinationImpl("Destination 2");
        activity1 = new ActivityImpl("Activity 1", "Description 1", 100.0, 5);
        activity2 = new ActivityImpl("Activity 2", "Description 2", 200.0, 3);

        destination1.addActivity(activity1);
        destination2.addActivity(activity2);

        itinerary.add(destination1);
        itinerary.add(destination2);

        travelPackage = new TravelPackageImpl("Travel Package", 10, itinerary);
        System.out.println(travelPackage.getItinerary().get(0).getName());
    }

    @Test
    public void testGetName() {
        assertEquals("Travel Package", travelPackage.getName());
    }

    @Test
    public void testGetPassengerCapacity() {
        assertEquals(10, travelPackage.getPassengerCapacity());
    }

    @Test
    public void testGetItinerary() {
        assertEquals(itinerary, travelPackage.getItinerary());
    }

    @Test
    public void testGetPassengers() {
        List<Passenger> passengers = new ArrayList<>();
        assertEquals(passengers, travelPackage.getPassengers());
    }

    @Test
    public void testAddDestination() {
        Destination destination3 = new DestinationImpl("Destination 3");
        travelPackage.addDestination(destination3);

        assertTrue(travelPackage.getItinerary().contains(destination3));
    }

    @Test
    public void testAddPassenger() {
        Passenger passenger1 = new StandardPassenger("Passenger 1", 1001, 1000);
        travelPackage.addPassenger(passenger1);

        assertTrue(travelPackage.getPassengers().contains(passenger1));
    }

    @Test
    public void testFindPassengerByNumber() {
        Passenger passenger1 = new StandardPassenger("Passenger 1", 1001, 1000);
        travelPackage.addPassenger(passenger1);

        Passenger foundPassenger = travelPackage.findPassengerByNumber(1001);
        assertEquals(passenger1, foundPassenger);
    }

    @Test
    public void testSignUpPassengerForActivity_Success() {
        Passenger passenger1 = new GoldPassenger("Passenger 1", 1001, 1500);
        travelPackage.addPassenger(passenger1);

        assertTrue(travelPackage.signUpPassengerForActivity(passenger1, activity1));
    }

    @Test
    public void testAddDuplicateDestination() {
        Destination destination3 = new DestinationImpl("Destination 1");
        travelPackage.addDestination(destination3);

        assertEquals(2, travelPackage.getItinerary().size());
    }

    @Test
    public void testAddMorePassengersThanCapacity() {
        Passenger passenger1 = new StandardPassenger("Passenger 1", 1001, 1000);
        Passenger passenger2 = new StandardPassenger("Passenger 2", 1002, 1000);
        Passenger passenger3 = new StandardPassenger("Passenger 3", 1003, 1000);
        Passenger passenger4 = new StandardPassenger("Passenger 4", 1004, 1000);
        Passenger passenger5 = new StandardPassenger("Passenger 5", 1005, 1000);
        Passenger passenger6 = new StandardPassenger("Passenger 6", 1006, 1000);
        Passenger passenger7 = new StandardPassenger("Passenger 7", 1007, 1000);
        Passenger passenger8 = new StandardPassenger("Passenger 8", 1008, 1000);
        Passenger passenger9 = new StandardPassenger("Passenger 9", 1009, 1000);
        Passenger passenger10 = new StandardPassenger("Passenger 10", 10010, 1000);

        travelPackage.addPassenger(passenger1);
        travelPackage.addPassenger(passenger2);
        travelPackage.addPassenger(passenger3);
        travelPackage.addPassenger(passenger4);
        travelPackage.addPassenger(passenger5);
        travelPackage.addPassenger(passenger6);
        travelPackage.addPassenger(passenger7);
        travelPackage.addPassenger(passenger8);
        travelPackage.addPassenger(passenger9);
        travelPackage.addPassenger(passenger10);

        assertEquals(10, travelPackage.getPassengers().size());

        // Attempt to add another passenger, but it should not be allowed due to capacity limit
        Passenger passenger11 = new StandardPassenger("Passenger 11", 1011, 1000);

        assertFalse(travelPackage.addPassenger(passenger11));
    }

    @Test
    public void testFindNonExistentPassengerByNumber() {
        Passenger foundPassenger = travelPackage.findPassengerByNumber(9999);
        assertNull(foundPassenger);
    }

    @Test
    public void testSignUpPassengerForFullActivity() {
        Passenger passenger1 = new GoldPassenger("Passenger 1", 1001, 1000);
        Passenger passenger2 = new GoldPassenger("Passenger 2", 1002, 1000);
        Passenger passenger3 = new GoldPassenger("Passenger 3", 1003, 1000);
        travelPackage.addPassenger(passenger1);
        travelPackage.addPassenger(passenger2);
        travelPackage.addPassenger(passenger3);

        // Sign up passenger1 for the activity until the capacity is full
        assertTrue(travelPackage.signUpPassengerForActivity(passenger1, activity2));
        assertTrue(travelPackage.signUpPassengerForActivity(passenger2, activity2));
        assertTrue(travelPackage.signUpPassengerForActivity(passenger3, activity2));


        // Attempt to sign up passenger1 again, but it should fail as the activity is already full
        assertFalse(travelPackage.signUpPassengerForActivity(passenger1, activity2));

    }

    @Test
    public void testSignUpPassengerForActivityWithLowBalance() {
        Passenger passenger1 = new StandardPassenger("Passenger 1", 1001, 0);
        travelPackage.addPassenger(passenger1);

        // The activity1 has a cost of 100, but passenger1 has no balance
        assertFalse(travelPackage.signUpPassengerForActivity(passenger1, activity1));
    }

    @Test
    public void testSignUpPassengerForActivityWithoutTravelPackage() {
        Passenger passenger1 = new StandardPassenger("Passenger 1", 1001, 0);

        // The activity1 has a cost of 100, but passenger1 has no balance
        assertFalse(travelPackage.signUpPassengerForActivity(passenger1, activity1));
    }
}
