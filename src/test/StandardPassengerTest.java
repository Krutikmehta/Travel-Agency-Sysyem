package test;

import org.junit.jupiter.api.Test;
import passenger.Passenger;
import passenger.StandardPassenger;

import static org.junit.jupiter.api.Assertions.*;

public class StandardPassengerTest {

    @Test
    public void testSignUpForActivityWithSufficientBalance() {
        Passenger passenger = new StandardPassenger("John", 123, 1000);

        assertTrue(passenger.signUpForActivity(500));
        assertEquals(500, passenger.getBalance());
    }

    @Test
    public void testSignUpForActivityWithInsufficientBalance() {
        Passenger passenger = new StandardPassenger("John", 123, 100);

        assertFalse(passenger.signUpForActivity(500));
        assertEquals(100, passenger.getBalance());
    }
}

