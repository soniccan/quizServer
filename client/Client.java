package client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import client.listenThread;

 
public class Client {
 
    public static final int ECHO_PORT = 10007;
    public static final int BUF_SIZE = 1000;
 
    public static void main(String[] args) {
        SocketChannel channel = null;
        ByteBuffer buf = ByteBuffer.allocate(BUF_SIZE);
        Charset charset = Charset.forName("UTF-8");
        String input;
        try {
            channel = SocketChannel.open(new InetSocketAddress("localhost",ECHO_PORT));
            BufferedReader keyin = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("1+1\n");
            new listenThread().run();

            // String line = keyin.readLine();
            // channel.write(charset.encode(CharBuffer.wrap(line + "\n")));
            // while (channel.isConnected()) {
            //     buf.clear();
            //     if (channel.read(buf) < 0) {
            //         return;
            //     }
            //     buf.flip();
            //     System.out.print("受信：" + charset.decode(buf).toString());
            // }
            
            
            

            


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (channel != null && channel.isOpen()) {
                try {
                    channel.close();
                } catch (IOException e) {}
            }
        }
    }

  
}


