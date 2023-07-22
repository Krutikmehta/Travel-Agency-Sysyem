package test;

import org.junit.jupiter.api.Test;
import passenger.Passenger;
import passenger.StandardPassenger;

import static org.junit.jupiter.api.Assertions.*;

public class PassengerTest {

    @Test
    public void testGetName() {
        Passenger passenger = new StandardPassenger("John", 123, 1000);
        assertEquals("John", passenger.getName());
    }

    @Test
    public void testGetPassengerNumber() {
        Passenger passenger = new StandardPassenger("John", 123, 1000);
        assertEquals(123, passenger.getPassengerNumber());
    }


    @Test
    public void testGetBalance() {
        Passenger passenger = new StandardPassenger("John", 123, 1000);
        assertEquals(1000, passenger.getBalance());
    }

    @Test
    public void testSetBalance() {
        Passenger passenger = new StandardPassenger("John", 123, 1000);
        passenger.setBalance(500);
        assertEquals(500, passenger.getBalance());
    }

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
