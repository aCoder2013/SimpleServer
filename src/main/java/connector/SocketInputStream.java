package connector;

import javax.servlet.ServletInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Song on 2015/9/2.
 */
public class SocketInputStream extends ServletInputStream {

    private InputStream inputStream = null;

    public SocketInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public int read() throws IOException {
        if(inputStream!=null){
            return inputStream.read();
        }
        return -1;

    }


}
