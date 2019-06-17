package sample;

import javafx.application.Application;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class SampleTest {
    @BeforeClass
    public static void init(){
        Thread thread = new Thread(){
            public void run(){
                Application.launch(Main.class);
            }
        };
        thread.setDaemon(true);
        thread.start();
    }
    @Test
    public void should_Success(){
        assertEquals(0, 0);
    }
}
