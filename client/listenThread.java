package client;
import java.lang.Thread;
import java.nio.channels.SocketChannel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
class listenThread extends Thread{
  
  public static final int ECHO_PORT = 10007;
  public static final int BUF_SIZE = 1000;
  private SocketChannel channel;

  ByteBuffer buf = ByteBuffer.allocate(BUF_SIZE);
  Charset charset = Charset.forName("UTF-8");
  String input;

  listenThread(SocketChannel channel ){
    this.channel = channel;
   
  public void run(){
    
    while ( (input = keyin.readLine()).length() > 0 ) {
      channel.write(charset.encode(CharBuffer.wrap(input + "\n")));
      while (channel.isConnected()) {
            buf.clear();
            if (channel.read(buf) < 0) {
                return;
            }
            buf.flip();
            input =charset.decode(buf).toString();
            System.out.print("受信：" + input );
            // 文字を処理するスレットとクイズ答えるスレッドを立てる。
            // if(input.charAt(0) =='1')
            // {
            //   new_
            // }


            
          }
    }
  }

}