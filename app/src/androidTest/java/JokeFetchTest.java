import android.test.ActivityInstrumentationTestCase2;

import com.udacity.gradle.builditbigger.MainActivity;

/**
 * Created by karan on 24-06-2017.
 */

public class JokeFetchTest extends ActivityInstrumentationTestCase2<MainActivity> {
    public JokeFetchTest(Class<MainActivity> activityClass) {
        super(activityClass);
    }

    public void asyncTaskTest() {
        MainActivity mainActivity = new MainActivity();
        assertNotNull(mainActivity.jokeFetched());
    }
}
