package http;

import java.io.*;

/**
 * Created by Song on 2015/8/31.
 */
public class Response {
    private static final int BUFFER_SIZE= 1024;

    private PrintWriter output;
    private Request request;

    public Response(PrintWriter output) {
        this.output =output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() throws IOException {
        if(output==null){
            return;
        }
        if(request.getUri()==null||request.getUri()==" "){
            request.setUri("/index.html");
        }
        char[] buffer = new char[BUFFER_SIZE];
        FileReader fileReader = null;
        try {
            File file = new File(HttpServer.WEEB_ROOT,request.getUri());
            if(file.exists()&&!file.isDirectory()){
                fileReader = new FileReader(file);
                int ch = fileReader.read(buffer,0,BUFFER_SIZE);
                while (ch!=-1){
                    output.write(buffer,0,ch);
                    ch = fileReader.read(buffer,0,BUFFER_SIZE);
                }
            }else {
                //ÎÄ¼þÎ´ÕÒµ½
                String errorMessage ="<h1>404<br>File Not Found</h1>";
                output.write(errorMessage);
            }
            output.flush();
        } catch (FileNotFoundException e) {
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
