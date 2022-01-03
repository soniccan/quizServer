package client;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ReadThread extends Thread {

    private Socket socket;

    public ReadThread(Socket socket)
    {
        this.socket = socket;
    }
    public void run()
    {
       
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while (( line = in.readLine()) != null ){
                System.out.println(line);

            }



        } catch (IOException e) {
            e.printStackTrace();
            } finally {
            try {
                if (socket != null) {
                socket.close();
                }
            } catch (IOException e) {}
            System.out.println("aaaaa "
                                + socket.getRemoteSocketAddress());
            }
        }

}
