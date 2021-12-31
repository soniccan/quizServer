package client;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class WriteThread extends Thread {

    private Socket socket;

    public WriteThread(Socket socket)
    {
        this.socket = socket;
    }

    public void run()
    {
       
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader keyIn = new BufferedReader(new InputStreamReader(System.in));
            String input;


            while ( (input = keyIn.readLine()).length() > 0 ) {
                out.println(input);
                // String line = in.readLine();
                // if (line != null) {
                //     System.out.println(line);
                // } else {
                // break;
                // }
            }

        } catch (IOException e) {
            e.printStackTrace();
            } finally {
            try {
                if (socket != null) {
                socket.close();
                }
            } catch (IOException e) {}
                System.out.println("接続が切れました "
                                + socket.getRemoteSocketAddress());
            }
        }

}
