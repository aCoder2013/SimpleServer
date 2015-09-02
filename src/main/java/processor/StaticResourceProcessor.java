package processor;

import http.HttpRequest;
import http.HttpResponse;

import java.io.IOException;

/**
 * 静态化资源处理器
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
     * 处理静态资源
     */
    public void process(){
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
