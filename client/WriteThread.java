package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.charset.Charset;


public class WriteThread extends Thread {


    
    DatagramChannel datagramChannel;
    InetSocketAddress inetSocketAddress;
    
    public WriteThread(DatagramChannel datagramChannel, InetSocketAddress inetSocketAddress) {
        this.datagramChannel = datagramChannel;
        this.inetSocketAddress = inetSocketAddress;
        
    }
    public void run()
    {  
        
     
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        Charset charset = Charset.forName("Shift-JIS");
        BufferedReader keyin = new BufferedReader(new InputStreamReader(System.in));
        String line;

        try {
            while((line = keyin.readLine()) != null)
            {
                
                byteBuffer = charset.encode(line);
                datagramChannel.send(byteBuffer, inetSocketAddress);
            }

           
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

   
