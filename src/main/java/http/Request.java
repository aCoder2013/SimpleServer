package http;


import org.omg.CORBA.ServerRequest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Song on 2015/8/31.
 */
public class Request implements ServletRequest{
    private InputStream input = null;
    private String uri = "";

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

    public Object getAttribute(String s) {
        return null;
    }

    public Enumeration getAttributeNames() {
        return null;
    }

    public String getCharacterEncoding() {
        return null;
    }

    public void setCharacterEncoding(String s) throws UnsupportedEncodingException {

    }

    public int getContentLength() {
        return 0;
    }

    public String getContentType() {
        return null;
    }

    public ServletInputStream getInputStream() throws IOException {
        return null;
    }

    public String getParameter(String s) {
        return null;
    }

    public Enumeration getParameterNames() {
        return null;
    }

    public String[] getParameterValues(String s) {
        return new String[0];
    }

    public Map getParameterMap() {
        return null;
    }

    public String getProtocol() {
        return null;
    }

    public String getScheme() {
        return null;
    }

    public String getServerName() {
        return null;
    }

    public int getServerPort() {
        return 0;
    }

    public BufferedReader getReader() throws IOException {
        return null;
    }

    public String getRemoteAddr() {
        return null;
    }

    public String getRemoteHost() {
        return null;
    }

    public void setAttribute(String s, Object o) {

    }

    public void removeAttribute(String s) {

    }

    public Locale getLocale() {
        return null;
    }

    public Enumeration getLocales() {
        return null;
    }

    public boolean isSecure() {
        return false;
    }

    public RequestDispatcher getRequestDispatcher(String s) {
        return null;
    }

    public String getRealPath(String s) {
        return null;
    }

    public int getRemotePort() {
        return 0;
    }

    public String getLocalName() {
        return null;
    }

    public String getLocalAddr() {
        return null;
    }

    public int getLocalPort() {
        return 0;
    }
}
