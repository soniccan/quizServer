package quizserver;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;


public class ServerThread extends Thread {
    private Socket socket;
    private Rank myrank;
    private short players;
    public ServerThread(Socket socket,Rank myrank,short players)
    {

        this.socket = socket;
        this.myrank = myrank;
        this.players = players;
        System.out.println("Accept " + socket.getRemoteSocketAddress());
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
                //out.printlnでも２スレッド目に送信できていない。
                myrank.send(out);



            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
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
    // public  void sendRank(PrintWriter out)
    // {
    //     this.rank++;
    //     out.println(this.rank);
    // }

    
}
