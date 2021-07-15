package quizserver;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;

public class MulticlientServer{
    public static final int PORT =1234;

    public static void main(String args[])
    {
        ServerSocket serverSocket = null;
        Socket socket = null;
        Rank myrank=null;
        try {
            myrank = new Rank();
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server up port="+ serverSocket.getLocalPort());
            while(true)
            {
                socket = serverSocket.accept();
                new ServerThread(socket,myrank).run();
            }

        }catch (IOException e)
        {
            e.printStackTrace();
        }finally
        {
            try{
                if(socket != null)
                {
                    socket.close();
                }
            }catch (IOException e){}
            try {
                if(serverSocket != null)
                {
                    serverSocket.close();
                }
            } catch (IOException e){}
        }
    }
    

}