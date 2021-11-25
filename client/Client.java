package client;

import java.net.Socket;

import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

public class Client {
  
  public static final int ECHO_PORT = 1235;

  public static boolean ans_is_true(String sol)
  {
    long end_point,start_point;
    String input,line;
    start_point = System.currentTimeMillis();

    // // while ( (input = keyIn.readLine()).length() > 0 ) {
    //   // if(input.equals(sol))
    //   {
    //     end_point =System.currentTimeMillis();
    //     long res = end_point - start_point;
    //     System.out.println("Ans is correct.time is " + res);
    //     return true;
    //   }
    //   else{
    //     System.out.println("Wrong answer");
    //     return false;
    //   }
  }

  public static void main(String args[]) {
    Socket socket = null;
    int ans_num=0;
    try {
      socket = new Socket(args[0], ECHO_PORT);
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      BufferedReader keyIn = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("接続しました"
                        + socket.getRemoteSocketAddress());
      String input;
      String line;
      

      String prestring =in.readLine();
      System.out.println(prestring);

      if((input = keyIn.readLine()).length() >0)
        out.println(input);

      System.out.println("かしこまりましたそれでは始めます。"+input+"さん");
        //問題文をクライアント側で表示している直したい
      System.out.println("1+1 is ?");


      if(ans_is_true("2"))
      {
        ans_num++;
      }else{
        System.out.println("wrong answer");
      }

      } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (socket != null) {
          socket.close();
        }
      } catch (IOException e) {}
      System.out.println("切断されました "+ socket.getRemoteSocketAddress());
    }
  }
}