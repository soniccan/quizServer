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

    public static void main(String args[]) {
        ServerSocket serverSocket = null;
        Rank rank =new Rank();
        short players =4;
        try {
            serverSocket = new ServerSocket(ECHO_PORT);
            System.out.println("EchoServerが起動しました(port=" + serverSocket.getLocalPort() + ")");
            while (true) {
                Socket socket = serverSocket.accept();
                new EchoThread(socket,rank,players).start();
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
    public EchoThread(Socket socket,Rank rank,short btm_rank) {
        this.socket = socket;
        this.rank =rank;
        this.btm_rank =btm_rank;
        this.players =4;
        System.out.println("接続されました " + socket.getRemoteSocketAddress());
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(socket.getRemoteSocketAddress() + " 受信: " + line);
                int myrank = rank.send(out);
                System.out.println(socket.getRemoteSocketAddress() + " 送信: rank is " + myrank);
                //もし全員回答したら一番早かった人の名前を全員に送る。たい。

                // if( btm_rank == players )
                // {

                // }
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