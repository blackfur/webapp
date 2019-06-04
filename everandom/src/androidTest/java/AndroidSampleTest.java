import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertEquals;

@RunWith(AndroidJUnit4.class)
public class AndroidSampleTest {
    @Test
    public void sample() throws Exception {
        assertEquals(1, 1);
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        Log.d(getClass().getSimpleName(), "Sample Android Test.");

        assertEquals("net.suicide.everandom", appContext.getPackageName());
    }
}
