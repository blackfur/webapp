import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import org.junit.Test;

public class Sample {
    @Test
    public void should_fetch_file() throws SftpException, JSchException {
        Sftper sftper = new Sftper();
        //sftper.download("demo", "test.rebex.net", 22, "password", "/pub/example/crackme.txt", "./crackme.txt");
        sftper.upload("demo",
                "test.rebex.net",
                22,
                "password",
                "./crackme.txt",
                "/pub/example/crackme.txt"
        );
    }
}
