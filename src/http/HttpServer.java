package http;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Song on 2015/8/31.
 */
public class HttpServer {
    /*
        webroot 是放置HTML，JS,CSS等文件的目录
        user.dir为项目根路径，eg:D:\AboutCoding\Java\GitProject\SImpleServer
     */
    public static final String WEEB_ROOT = System.getProperty("user.dir")
            + File.separator+"webroot";

    /*
        关闭Server的命令
     */
    private static final String SHUT_DOWN_COMMAND ="/shutdown";

    /*
        服务器是否关闭
     */
    private boolean shutdown = false;

    //监听的端口
    private int port = 8080;

    public void await() throws IOException {
        ServerSocket server = null;
        try{
            server = new ServerSocket(port,1, InetAddress.getByName("127.0.0.1"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while(!shutdown){
            Socket socket = null;
            InputStream in = null;
            OutputStream os = null;
            try {
                socket = server.accept();
                in = socket.getInputStream();
                os = socket.getOutputStream();
                //创建Request对象,解析输入流
                Request request = new Request(in);
                request.parse();
                //创建Response对象
                PrintWriter output = new PrintWriter(os);
                Response response = new Response(output);
                response.setRequest(request);
                response.sendStaticResource();
                //检查URI是否为关闭命令
                String uri = request.getUri();
                shutdown = (uri==null?false:uri.equals(SHUT_DOWN_COMMAND));
                PrintWriter writer = new PrintWriter(os);
                //关闭Socket
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }




}
