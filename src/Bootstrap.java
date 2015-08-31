import http.HttpServer;

import java.io.IOException;

/**
 * Created by Song on 2015/8/31.
 */
public class Bootstrap {

    public static void main(String[] args) throws IOException {
        HttpServer server = new HttpServer();
        server.await();
    }
}
