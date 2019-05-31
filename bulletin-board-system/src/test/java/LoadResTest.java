import org.junit.Test;
import static org.junit.Assert.assertTrue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.test.context.TestPropertySource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class LoadResTest {
	final String propfile = "app.prop";
	//final String propfile = "application.properties";
    @Test
    public void sample() throws IOException {
		 /*
        Resource[] fileResource= new PathMatchingResourcePatternResolver().getResources("classpath:" + propfile );
        if (fileResource != null && fileResource.length > 0) {
            Resource f = fileResource[0];
            if(f.exists()){
                InputStream in = f.getInputStream();
                log.info(toString(in));
                in.close();
            }else{
                log.info(propfile + " Not Found.");
            }
        }
		  */
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(propfile);
        log.info(toString(is));
        is.close();
    }
    String toString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
    }
}
