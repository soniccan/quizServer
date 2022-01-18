package quizserver;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

public class MulticlientServer {

public static final int ECHO_PORT = 10007;

public static void main(String args[]) {

        ServerSocket serverSocket = null;

        try {
            ServerData server_data = new ServerData();
            serverSocket = new ServerSocket(ECHO_PORT);
            System.out.println("EchoServerが起動しました" + "(port="
                                + serverSocket.getLocalPort() + ")");
        while (true) {
            Socket socket = serverSocket.accept();
            new EchoThread(socket,server_data).start();
        }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {}
        }
    }

}

class EchoThread extends Thread {

    private Socket socket;
    private ServerData server_date;

    public EchoThread(Socket socket, ServerData server_data) {
        this.socket = socket;
        this.server_date = server_data;
        System.out.println("接続されました "
                        + socket.getRemoteSocketAddress());
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String line;
            String messageParsed;

            while ( (line = in.readLine()) != null ) {
                    System.out.println(socket.getRemoteSocketAddress()
                                    + " 受信: " + line);
                    messageParsed = server_date.Parse(line,server_date);
                    System.out.println("send :" + messageParsed );
                    out.println(messageParsed);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
                } catch (IOException e) {}
                    System.out.println("切断されました "
                                + socket.getRemoteSocketAddress());
        }
    }



}

