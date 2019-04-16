package practise;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

class ClassLoaderTest {

    Logger log = Logger.getLogger(getClass().getSimpleName());

    @Test
    void sample() throws Throwable{
       Class c = ClassLoaderTest.class;
       log.info(c.getClassLoader());
       log.info(String.class.getClassLoader());
    }
}
