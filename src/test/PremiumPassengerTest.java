package test;

import org.junit.jupiter.api.Test;
import passenger.Passenger;
import passenger.PremiumPassenger;

import static org.junit.jupiter.api.Assertions.*;

public class PremiumPassengerTest {

    @Test
    public void testSignUpForActivity() {
        Passenger passenger = new PremiumPassenger("Bob", 789, 1000);

        assertTrue(passenger.signUpForActivity(500));
        assertEquals(1000, passenger.getBalance()); // Premium passenger can sign up for activities for free
    }
}
