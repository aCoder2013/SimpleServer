import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        boolean stop = false;
        ServerSocket server = new ServerSocket(8080,1, InetAddress.getByName("127.0.0.1"));
        while (!stop){
            Socket socket =server.accept();
            OutputStream os = socket.getOutputStream();
            PrintWriter out = new PrintWriter(os);
            InputStream is = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            out.print("<h1>Hello World</h1>");
            out.flush();
            boolean loop = true;
            StringBuffer sb = new StringBuffer();
            while(loop){
                if(reader.ready()){
                    int i =0;
                    while((i=reader.read())!=-1){
                        sb.append((char)i);
                    }
                    loop = false;
                }
                Thread.currentThread().sleep(500);
            }
            System.out.println(sb);
            out.close();
            is.close();
            socket.close();
        }
        server.close();
    }
}
