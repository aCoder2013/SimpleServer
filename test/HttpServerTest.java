import http.HttpServer;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Song on 2015/8/31.
 */
public class HttpServerTest {


    @Test
    public void awaitTest() throws IOException {
        HttpServer server  = new HttpServer();
        server.await();
    }

    @Test
    public void parseUriTest(){
        String str ="GET / HTTP/1.1";
        int index1 = str.indexOf(" ");
        if(index1!=-1){
            int index2 = str.indexOf(" ",index1+1);
            if(index1<index2){
                String string = str.substring(index1+1,index2);
                System.out.print(string.length());
            }
        }

    }
}
