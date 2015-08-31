package processor;

import http.Request;
import http.Response;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by Song on 2015/8/31.
 */
public class ServletProcessor {
    private ServletRequest request;
    private ServletResponse response;


    public ServletProcessor(ServletRequest request, ServletResponse response) {
        this.request = request;
        this.response= response;
    }
}
