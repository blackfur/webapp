import com.google.common.truth.Truth.assertThat;
import org.junit.Test;
import static System.out.println;

public class Sample {
    @Test
    public void emailValidator_CorrectEmailSimple_ReturnsTrue() {
       println("Sample Test.");
        assertThat(true).isTrue();
    }
   @Test(expected = NullPointerException.class)
   public void nullStringTest() {
      String str = null;
      assertTrue(str.isEmpty());
   }
   @Test(timeout = 1000)
    public void request(){}
}
