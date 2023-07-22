package test;

import activity.Activity;
import activity.ActivityImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import passenger.GoldPassenger;
import passenger.Passenger;
import passenger.PremiumPassenger;
import passenger.StandardPassenger;

import static org.junit.jupiter.api.Assertions.*;

public class ActivityImplTest {

    private Activity activity;
    private Passenger standardPassenger;
    private Passenger goldPassenger;
    private Passenger premiumPassenger;

    @BeforeEach
    void setUp() {
        activity = new ActivityImpl("Hiking", "Enjoy hiking in the mountains", 50.0, 5);
        standardPassenger = new StandardPassenger("John", 1001, 100);
        goldPassenger = new GoldPassenger("Emma", 2001, 200);
        premiumPassenger = new PremiumPassenger("Sophia", 3001,0);
    }

    @Test
    void testGetName() {
        assertEquals("Hiking", activity.getName());
    }

    @Test
    void testGetDescription() {
        assertEquals("Enjoy hiking in the mountains", activity.getDescription());
    }

    @Test
    void testGetCost() {
        assertEquals(50.0, activity.getCost());
    }

    @Test
    void testGetCapacity() {
        assertEquals(5, activity.getCapacity());
    }

    @Test
    void testIsAvailable() {
        assertTrue(activity.isAvailable());
    }

    @Test
    void testSignUpPassengerStandardPassengerSuccess() {
        assertTrue(activity.signUpPassenger(standardPassenger));
        assertEquals(4, activity.getCapacity());
        assertEquals(50.0, standardPassenger.getBalance());
    }

    @Test
    void testSignUpPassengerGoldPassengerSuccess() {
        assertTrue(activity.signUpPassenger(goldPassenger));
        assertEquals(4, activity.getCapacity());
        assertEquals(155, goldPassenger.getBalance());
    }

    @Test
    void testSignUpPassengerPremiumPassengerSuccess() {
        assertTrue(activity.signUpPassenger(premiumPassenger));
        assertEquals(4, activity.getCapacity());
        assertEquals(0.0, premiumPassenger.getBalance());
    }

    @Test
    void testSignUpPassengerInsufficientBalance() {
        standardPassenger.setBalance(30.0);
        assertFalse(activity.signUpPassenger(standardPassenger));
        assertEquals(5, activity.getCapacity());
        assertEquals(30.0, standardPassenger.getBalance());
    }

    @Test
    void testSignUpPassengerCapacityReached() {
        activity=new ActivityImpl("Hiking", "Enjoy hiking in the mountains", 50.0, 0);

        assertFalse(activity.signUpPassenger(standardPassenger));
        assertEquals(0, activity.getCapacity());
        assertEquals(100.0, standardPassenger.getBalance());
    }
}
