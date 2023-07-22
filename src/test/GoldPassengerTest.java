package test;

import org.junit.jupiter.api.Test;
import passenger.GoldPassenger;
import passenger.Passenger;

import static org.junit.jupiter.api.Assertions.*;

public class GoldPassengerTest {

    @Test
    public void testSignUpForActivityWithSufficientBalance() {
        Passenger passenger = new GoldPassenger("Alice", 456, 1000);

        assertTrue(passenger.signUpForActivity(500));
        assertEquals(550, passenger.getBalance());
    }

    @Test
    public void testSignUpForActivityWithInsufficientBalance() {
        Passenger passenger = new GoldPassenger("Alice", 456, 100);

        assertFalse(passenger.signUpForActivity(500));
        assertEquals(100, passenger.getBalance());
    }
}

