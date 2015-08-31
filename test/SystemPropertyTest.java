import org.junit.Assert;
import org.junit.Test;
import sun.rmi.runtime.Log;

/**
 * Created by Song on 2015/8/31.
 */
public class SystemPropertyTest {

   @Test
   public void testUserDir(){
      String value = System.getProperty("user.dir");
      System.out.println(value);
   }



}
