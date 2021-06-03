package quizserver;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;


public class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket)
    {
        this.socket = socket;
        System.out.println("Accept "+socket.getRemoteSocketAddress());
    }

    public void run()
    {
        try{
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String line;
            while((line = in.readLine()) != null)
            {
                System.out.println(socket.getRemoteSocketAddress() + "受信: " + line);
                out.println(line);
                System.out.println(socket.getRemoteSocketAddress() + "送信: " + line);
                
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }finally
        {
            try
            {
                if(socket != null)
                {
                    socket.close();
                }
            } catch (IOException e)
            {
                System.out.println("切断されました" + socket.getRemoteSocketAddress());
            }
        }

    }

    
}
