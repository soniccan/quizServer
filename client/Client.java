
package client;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.charset.Charset;
import java.util.Iterator;



 public class Client {

    public static void main(String[] args) {
         DatagramChannel datagramChannel = null;
        try {
            datagramChannel = DatagramChannel.open();
            InetSocketAddress inetSocketAddress = new InetSocketAddress(10007);
            // datagramChannel.socket().bind(inetSocketAddress);

            System.out.println("OK bind");
            System.out.println(inetSocketAddress.getHostName());
            datagramChannel.configureBlocking(false);
            Selector selector = Selector.open();

            datagramChannel.register(selector, SelectionKey.OP_READ);
            new WriteThread(datagramChannel,inetSocketAddress).start();

             while (selector.select() > 0) {
                 for (Iterator iterator 
                      = selector.selectedKeys().iterator();
                                       iterator.hasNext();) {
                     SelectionKey selectionKey = (SelectionKey)iterator.next();
                     iterator.remove();
                     if (selectionKey.isReadable()) {
                         doReceive(selectionKey);
                     }

                    //  if (selectionKey.isWritable()) {
                    //     doWrite(selectionKey);
                    // }
                 }
             }
         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             try {
                 datagramChannel.close();
             } catch (Exception e) {}
         }


     }


     private static void doReceive(SelectionKey selectionKey) {
         DatagramChannel datagramChannel 
                     = (DatagramChannel) selectionKey.channel();
         ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
         Charset charset = Charset.forName("Shift-JIS");
         try {
             datagramChannel.receive(byteBuffer);
             byteBuffer.flip();
             System.out.println(charset.decode(byteBuffer));
         } catch (Exception e) {
             e.printStackTrace();
         }
     }


     private static void doWrite(SelectionKey selectionKey) {
        
    }
 }