package quizserver;

import java.net.Socket;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

public class MulticlientServer {

    public static final int ECHO_PORT = 1234;

    public static void main(String args[]) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(ECHO_PORT);
            System.out.println("EchoServerが起動しました(port=" + serverSocket.getLocalPort() + ")");
            while (true) {
                Socket socket = serverSocket.accept();
                new EchoThread(socket).start();
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

    public EchoThread(Socket socket) {
        this.socket = socket;
        System.out.println("接続されました " + socket.getRemoteSocketAddress());
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(socket.getRemoteSocketAddress() + " 受信: " + line);
                out.println(line);
                System.out.println(socket.getRemoteSocketAddress() + " 送信: " + line);
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