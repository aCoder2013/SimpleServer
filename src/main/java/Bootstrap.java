import connector.HttpConnector;

import java.io.IOException;

/**
 * ∆Ù∂Ø¿‡
 * Created by Song on 2015/8/31.
 */
public class Bootstrap {

    public static void main(String[] args) throws IOException {
        HttpConnector connector  = new HttpConnector();
        connector.start();
    }
}
