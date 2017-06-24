import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertTrue;

/**
 * Created by karan on 24-06-2017.
 */
@RunWith(AndroidJUnit4.class)
public class JokeFetchTest {

    @Test
    public void asyncTaskTest() throws Exception {
        MainActivity mainActivity = new MainActivity();
        mainActivity.testFlag = true;
        new EndpointsAsyncTask().execute(mainActivity);
        Thread.sleep(5000);
        assertTrue("Error: Fetched joke: " + MainActivity.loadedJoke, MainActivity.loadedJoke != null);
    }
}
