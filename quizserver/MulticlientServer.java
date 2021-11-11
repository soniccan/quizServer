package quizserver;

import java.net.Socket;
import jdk.jfr.Unsigned;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

public class MulticlientServer {

    public static final int ECHO_PORT = 1235;
    public static final short PLAYERS = 2;

    public static void main(String args[]) {
        ServerSocket serverSocket = null;
        Rank rank =new Rank();
        short  p_num =0;
        
        try {
            serverSocket = new ServerSocket(ECHO_PORT);
            System.out.println("EchoServerが起動しました(port=" + serverSocket.getLocalPort() + ")");
            

            while (true) {
                if( p_num >= PLAYERS )
                {
                    System.out.println("All gathered!!");
                    if (serverSocket != null) {
                        serverSocket.close();
                    }
                    break;
                }
                Socket socket =serverSocket.accept();
                //playerの参加数をここで増やしている。
                p_num = rank.player_inc();
                new EchoThread(socket,rank,PLAYERS,p_num).start();
            }
    

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
            }
        }
    }

}

class EchoThread extends Thread {

    private Socket socket;
    private Rank rank;
    private short btm_rank;
    private short players;
    private short p_num;
    
    public EchoThread(Socket socket,Rank rank,short btm_rank,short  p_num) {
        this.socket = socket;
        this.rank = rank;
        this.btm_rank = btm_rank;
        // Todo：後出直す↓
        this.players = 2;
        this.p_num = p_num;
        System.out.println("接続されました " + socket.getRemoteSocketAddress());
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String line;
            out.println("今回の参加者は"+players+"人です。あなたは"+p_num+"人目です。あなたのお名前はなんですか");
            String name =in.readLine();
            while ((line = in.readLine()) != null) {
                System.out.println(socket.getRemoteSocketAddress() + " 受信: " + line);
                
                System.out.println(socket.getRemoteSocketAddress() + " 送信: rank is ");



                int myrank = rank.send(out);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
            }
            System.out.println("切断されました ");
        }
    }
}