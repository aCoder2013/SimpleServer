package processor;

import http.HttpRequest;
import http.HttpResponse;

import java.io.IOException;

/**
 * ��̬����Դ������
 * Created by Song on 2015/8/31.
 */
public class StaticResourceProcessor {

    private HttpRequest request;
    private HttpResponse response ;

    public StaticResourceProcessor(HttpRequest request, HttpResponse response) {
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
