package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;
 
public class UdpServer {
 
	public static void main(String[] args) throws IOException {
		DatagramSocket sock = new DatagramSocket(10007);//10005ポートでUDP受信用ソケット構築
		byte[] data = new byte[1024];//受信最大バッファ
		DatagramPacket packet = new DatagramPacket(data, data.length);//受信用パケットを構築
		System.out.println("セットアップOK");
		sock.receive(packet);//受信
		System.out.println("UDP受信:"+new String(Arrays.copyOf(packet.getData(),packet.getLength()),"UTF-8"));//受信データの表示
		sock.close();//ソケットのクローズ
	}
}