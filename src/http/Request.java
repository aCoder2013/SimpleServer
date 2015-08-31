package http;


import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Song on 2015/8/31.
 */
public class Request {
    private InputStream input = null;
    private String uri = null;

    public Request(InputStream in) {
        this.input = in;
    }


    /*
        解析输入流
     */
    public void parse() throws IOException {
        StringBuffer requestString = new StringBuffer();
        byte[] buffer = new byte[2048];
        int i =0;
        try {
            i = input.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            i=-1;
        }
        for(int j =0;j<i;j++){
            byte temp = buffer[j];
            requestString.append((char)temp);
        }
        parseUri(requestString.toString());
    }

    /*
        解析请求头，得到uri
     */
    private void parseUri(String requestString) {
        int index1 = requestString.indexOf(" ");
        if(index1!=-1){
            int index2 = requestString.indexOf(" ",index1+1);
            if(index1<index2){
                this.uri = requestString.substring(index1+1,index2);
            }
        }
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
