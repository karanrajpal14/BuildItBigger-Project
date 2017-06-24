import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.EndpointsAsyncTask;

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
        new EndpointsAsyncTask().execute(InstrumentationRegistry.getTargetContext()).get();
        assertTrue("Error: Fetched joke: " + EndpointsAsyncTask.fetchedJoke, EndpointsAsyncTask.fetchedJoke != null);
    }
}
