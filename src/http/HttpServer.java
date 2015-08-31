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
        webroot �Ƿ���HTML��JS,CSS���ļ���Ŀ¼
        user.dirΪ��Ŀ��·����eg:D:\AboutCoding\Java\GitProject\SImpleServer
     */
    public static final String WEEB_ROOT = System.getProperty("user.dir")
            + File.separator+"webroot";

    /*
        �ر�Server������
     */
    private static final String SHUT_DOWN_COMMAND ="/shutdown";

    /*
        �������Ƿ�ر�
     */
    private boolean shutdown = false;

    //�����Ķ˿�
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
                //����Request����,����������
                Request request = new Request(in);
                request.parse();
                //����Response����
                PrintWriter output = new PrintWriter(os);
                Response response = new Response(output);
                response.setRequest(request);
                response.sendStaticResource();
                //���URI�Ƿ�Ϊ�ر�����
                String uri = request.getUri();
                shutdown = (uri==null?false:uri.equals(SHUT_DOWN_COMMAND));
                PrintWriter writer = new PrintWriter(os);
                //�ر�Socket
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }




}
