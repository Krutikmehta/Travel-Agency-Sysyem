package test;

import activity.Activity;
import activity.ActivityImpl;
import destination.Destination;
import destination.DestinationImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DestinationImplTest {

    private Destination destination;

    @BeforeEach
    void setUp() {
        destination = new DestinationImpl("Mountain Resort");
    }

    @Test
    void testGetName() {
        assertEquals("Mountain Resort", destination.getName());
    }

    @Test
    void testGetActivitiesEmpty() {
        assertTrue(destination.getActivities().isEmpty());
    }

    @Test
    void testAddActivity() {
        Activity activity = new ActivityImpl("Hiking", "Enjoy hiking in the mountains", 50.0, 5);
        destination.addActivity(activity);

        assertFalse(destination.getActivities().isEmpty());
        assertEquals(1, destination.getActivities().size());
        assertEquals("Hiking", destination.getActivities().get(0).getName());
    }

    @Test
    void testFindActivityByNameExistingActivity() {
        Activity activity1 = new ActivityImpl("Hiking", "Enjoy hiking in the mountains", 50.0, 5);
        Activity activity2 = new ActivityImpl("Skiing", "Have fun skiing in the snow", 80.0, 10);

        destination.addActivity(activity1);
        destination.addActivity(activity2);

        Activity foundActivity = destination.findActivityByName("Hiking");
        assertNotNull(foundActivity);
        assertEquals("Hiking", foundActivity.getName());
    }

    @Test
    void testFindActivityByNameNonExistingActivity() {
        Activity activity1 = new ActivityImpl("Hiking", "Enjoy hiking in the mountains", 50.0, 5);
        Activity activity2 = new ActivityImpl("Skiing", "Have fun skiing in the snow", 80.0, 10);

        destination.addActivity(activity1);
        destination.addActivity(activity2);

        Activity foundActivity = destination.findActivityByName("Swimming");
        assertNull(foundActivity);
    }
}
