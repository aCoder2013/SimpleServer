package http;

import util.Constants;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Locale;

/**
 * Created by Song on 2015/9/2.
 */
public class HttpResponse implements HttpServletResponse {

    private static final int BUFFER_SIZE = 1024;
    private PrintWriter output;

    private HttpRequest request;




    public HttpResponse(OutputStream output) {
        this.output = new PrintWriter(output,true);
    }

    public void addCookie(Cookie cookie) {

    }

    public boolean containsHeader(String name) {
        return false;
    }

    public String encodeURL(String url) {
        return null;
    }

    public String encodeRedirectURL(String url) {
        return null;
    }

    public String encodeUrl(String url) {
        return null;
    }

    public String encodeRedirectUrl(String url) {
        return null;
    }

    public void sendError(int sc, String msg) throws IOException {

    }

    public void sendError(int sc) throws IOException {

    }

    public void sendRedirect(String location) throws IOException {

    }

    public void setDateHeader(String name, long date) {

    }

    public void addDateHeader(String name, long date) {

    }

    public void setHeader(String name, String value) {

    }

    public void addHeader(String name, String value) {

    }

    public void setIntHeader(String name, int value) {

    }

    public void addIntHeader(String name, int value) {

    }

    public void setStatus(int sc) {

    }

    public void setStatus(int sc, String sm) {

    }

    public String getCharacterEncoding() {
        return null;
    }

    public String getContentType() {
        return null;
    }

    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    public PrintWriter getWriter() throws IOException {
        return output;
    }

    public void setCharacterEncoding(String charset) {

    }

    public void setContentLength(int len) {

    }

    public void setContentType(String type) {

    }

    public void setBufferSize(int size) {

    }

    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    public int getBufferSize() {
        return 0;
    }

    public void flushBuffer() throws IOException {

    }

    public void resetBuffer() {

    }

    public boolean isCommitted() {
        return false;
    }

    public void reset() {

    }

    public void setLocale(Locale loc) {

    }

    public Locale getLocale() {
        return null;
    }

    public void sendStaticResource() throws IOException {
        if(output==null){
            return;
        }
        char[] buffer = new char[BUFFER_SIZE];
        FileReader fileReader = null;
        try {
            File file = new File(Constants.WEEB_ROOT,getRequest().getRequestURI());
            if(file.exists()&&!file.isDirectory()){
                fileReader = new FileReader(file);
                int ch = fileReader.read(buffer,0,BUFFER_SIZE);
                while (ch!=-1){
                    output.write(buffer,0,ch);
                    ch = fileReader.read(buffer,0,BUFFER_SIZE);
                }
            }else {
                //文件未找到
                String errorMessage ="<h1>404<br>File Not Found</h1>";
                output.write(errorMessage);
            }
            output.flush();
        } catch (FileNotFoundException e) {
            //文件未找到
            String errorMessage ="<h1>404<br>File Not Found</h1>";
            output.write(errorMessage);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fileReader!=null){
                fileReader.close();
            }
        }
    }
}
