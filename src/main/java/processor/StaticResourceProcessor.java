package processor;

import http.Request;
import http.Response;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * ��̬����Դ������
 * Created by Song on 2015/8/31.
 */
public class StaticResourceProcessor {

    private Request request;
    private Response response ;

    public StaticResourceProcessor(Request request, Response response) {
            this.request = request;
            this.response = response;
    }

    /**
     * ����̬��Դ
     */
    public void process(){
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
