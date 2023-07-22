package destination;

import java.util.List;
import activity.Activity;

public interface Destination {
    String getName();
    List<Activity> getActivities();
    void addActivity(Activity activity);
    Activity findActivityByName(String activityName);

}
