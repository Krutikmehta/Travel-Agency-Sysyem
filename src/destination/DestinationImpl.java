package destination;

import java.util.ArrayList;
import java.util.List;
import activity.Activity;

public class DestinationImpl implements Destination {
    private String name;
    private List<Activity> activities;

    public DestinationImpl(String name) {
        this.name = name;
        this.activities = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Activity> getActivities() {
        return activities;
    }

    @Override
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    @Override
    public Activity findActivityByName(String activityName) {
        for (Activity activity : activities) {
            if (activity.getName().equalsIgnoreCase(activityName)) {
                return activity;
            }
        }
        return null;
    }
}
