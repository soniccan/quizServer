// package new_server;
  
// import java.io.IOException;
// import java.net.InetSocketAddress;
// import java.nio.ByteBuffer;
// import java.nio.channels.SelectionKey;
// import java.nio.channels.Selector;
// import java.nio.channels.ServerSocketChannel;
// import java.nio.channels.SocketChannel;
// import java.nio.charset.Charset;
// import java.util.Iterator;
 
// public class NonblockingServer {
 
//     private static final int ECHO_PORT = 10007;
//     private static final int BUF_SIZE = 1000;
 
//     private Selector selector;
 
//     public static void main(String[] args) {
//         new NonblockingServer().run();
//     }
 
//     public void run() {
//         ServerSocketChannel serverChannel = null;
//         try {
//             selector = Selector.open();
//             serverChannel = ServerSocketChannel.open();
//             serverChannel.configureBlocking(false);
//             serverChannel.socket().bind(new InetSocketAddress(ECHO_PORT));
//             serverChannel.register(selector, SelectionKey.OP_ACCEPT);
//             System.out.println("NonBlockingChannelEchoServerが起動しました(port="
//                     + serverChannel.socket().getLocalPort() + ")");
//             while (selector.select() > 0) {
//                 for (Iterator it = selector.selectedKeys().iterator(); it.hasNext();) {
//                     SelectionKey key = (SelectionKey) it.next();
//                     it.remove();
//                     if (key.isAcceptable()) {
//                         doAccept((ServerSocketChannel) key.channel());
//                     } else if (key.isReadable()) {
//                         doRead((SocketChannel) key.channel());
//                     }
//                 }
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         } finally {
//             if (serverChannel != null && serverChannel.isOpen()) {
//                 try {
//                     System.out.println("NonBlockingChannelEchoServerを停止します。");
//                     serverChannel.close();
//                 } catch (IOException e) {}
//             }
//         }
//     }
 
//     private void doAccept(ServerSocketChannel serverChannel) {
//         try {
//             SocketChannel channel = serverChannel.accept();
//             String remoteAddress = channel.socket()
//                     .getRemoteSocketAddress()
//                     .toString();
//             System.out.println(remoteAddress + ":[接続されました]");
//             channel.configureBlocking(false);
//             channel.register(selector, SelectionKey.OP_READ);
 
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
 
//     private void doRead(SocketChannel channel) {
//         ByteBuffer buf = ByteBuffer.allocate(BUF_SIZE);
//         Charset charset = Charset.forName("UTF-8");
//         String remoteAddress = channel.socket()
//                 .getRemoteSocketAddress()
//                 .toString();
//         try {
//             if (channel.read(buf) < 0) {
//                 return;
//             }
//             buf.flip();
//             System.out.print(remoteAddress + ":"
//                     + charset.decode(buf).toString());
//             buf.flip();
//             channel.write(buf);
//         } catch (IOException e) {
//             e.printStackTrace();
//         } finally {
//             System.out.println(remoteAddress + ":[切断しました]");
//             try {
//                 channel.close();
//             } catch (IOException e) {}
//         }
//     }
 
// }

package new_server;
import java.net.InetSocketAddress;
 import java.nio.ByteBuffer;
 import java.nio.channels.DatagramChannel;
 import java.nio.channels.SelectionKey;
 import java.nio.channels.Selector;
 import java.nio.charset.Charset;
 import java.util.Iterator;


 public class NonblockingServer {
     public static void main(String[] args) {
         DatagramChannel datagramChannel = null;
         try {
             datagramChannel = DatagramChannel.open();
             InetSocketAddress inetSocketAddress = new InetSocketAddress(10007);
             System.out.println(inetSocketAddress.getHostName() + " " + inetSocketAddress.getPort() +"Open");
             datagramChannel.socket().bind(inetSocketAddress);
             datagramChannel.configureBlocking(false);
             Selector selector = Selector.open();
             datagramChannel.register(selector, SelectionKey.OP_READ);
             while (selector.select() > 0) {
                 for (Iterator iterator = selector.selectedKeys().iterator();iterator.hasNext();) {
                     
                    SelectionKey selectionKey = (SelectionKey)iterator.next();
                    iterator.remove();
                    if (selectionKey.isReadable()) {
                        doReceive(selectionKey);
                    }
                    
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
 }