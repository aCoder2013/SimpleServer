package connector;

import processor.HttpProcessor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Song on 2015/9/2.
 */
public class HttpConnector implements Runnable{

    private boolean stopped;
    private String scheme ="http";
    private int port = 8080;

    public String getScheme() {
        return scheme;
    }

    public int getPort() {
        return port;
    }

    public void run() {
        ServerSocket serverSocket =null;
        try {
            serverSocket = new ServerSocket(port,1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (!stopped) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                HttpProcessor processor = new HttpProcessor(this);
                processor.process(socket);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    public void start(){
        Thread thread = new Thread(this);
        thread.start();
    }


}
