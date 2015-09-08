package processor;

import connector.HttpConnector;
import connector.SocketInputStream;
import http.HttpRequest;
import http.HttpResponse;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Song on 2015/9/2.
 */
public class HttpProcessor {
    private HttpConnector connector;
    private HttpRequest request;
    private HttpResponse response;

    public HttpProcessor(HttpConnector httpConnector) {
        this.connector = httpConnector;
    }


    public void process(Socket socket) {
        try {
            byte[] buff = new byte[512];
            SocketInputStream input = new SocketInputStream(socket.getInputStream());
            request = new HttpRequest();
            response = new HttpResponse(socket.getOutputStream());
            response.setRequest(request);
            int i = input.readLine(buff, 0, buff.length);
            if (i != -1) {
                parseRequest(buff,i);//����������:GET /form.html HTTP/1.1
            }
            //��������ͷ
//            parseHeaders(input,buff);
            if(request.getRequestURI().startsWith("/servlet/")){
                ServletProcessor processor = new ServletProcessor(request,response);
                processor.process();
            }else  {
                StaticResourceProcessor processor = new StaticResourceProcessor(request,response);
                processor.process();
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ��������ͷ
     * @param inputStream
     */
    private void parseHeaders(SocketInputStream inputStream,byte[] buff) {

        try {
            int i  = inputStream.readLine(buff,0,buff.length);
            for(;i!=-1;i=inputStream.readLine(buff,0,buff.length)){
                StringBuffer sb = new StringBuffer(i);
                for(int j = 0;j<i;j++){
                    sb.append((char)buff[j]);
                }
                String headerString = sb.toString();
                int index = headerString.indexOf(":");
                if(index!=-1){
                    String key = headerString.substring(0,index);
                    String value = headerString.substring(index+2,headerString.length()-2);
                    request.addHeader(key,value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ����������
     * @param buff
     * @param len
     */
    private void parseRequest(byte[] buff, int len) {
        StringBuffer sb = new StringBuffer(len);
        for(int i =0;i<len;i++){
            sb.append((char)buff[i]);
        }
        int index = sb.indexOf(" ");
        String method = sb.substring(0,index);
        String uri ="" ;
        int index1  = sb.indexOf("/",index);
        int index2 = -1;
        if(index1!=-1) {
            index2 = sb.indexOf(" ", index1);
        }
        if(index1<index2){
            uri  = sb.substring(index1,index2);
        }
        //���URI�Ƿ��HTTPЭ��ǰ׺
        if(!uri.startsWith("/")){
            int pos = uri.indexOf("://");
            if(pos!=-1){
                pos = uri.indexOf("/",pos+3);
                if(pos==-1){
                    uri="";
                }else {
                    uri = uri.substring(pos);
                }

            }
        }
        int question = sb.indexOf("?");
        String queryString ="";
        if(question!=-1){
            queryString = sb.substring(question+1,index2);//�������
        }
        request.setQueryString(queryString);
        request.setRequestedUri(uri);
        request.setMethod(method);
    }
}