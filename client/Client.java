package client;

import java.net.Socket;
 import java.net.ServerSocket;
 import java.io.BufferedReader;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.io.IOException;
  
 public class Client {
  
  public static final int ECHO_PORT = 1235;
 
  public static void main(String args[]) {
    Socket socket = null;
    try {
      socket = new Socket(args[0], ECHO_PORT);
      System.out.println("接続しました"
                        + socket.getRemoteSocketAddress());
      
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      BufferedReader keyIn = new BufferedReader(new InputStreamReader(System.in));
      String input;

        //問題文をクライアント側で表示している直したい
      System.out.println("1+1 is ?");

      
      long end_point,start_point;

      start_point = System.currentTimeMillis();

      while ( (input = keyIn.readLine()).length() > 0 ) {
        // out.println(input);
        // String line = in.readLine();
        // if (line != null) {
        //   System.out.println(line);
        // } else {
        //   break;
        // }

        if(input.equals("2"))
        {
          end_point =System.currentTimeMillis();
          long res =end_point -start_point;

          System.out.println("Ans is correct.time is " +res);
          out.println(res);
          String line = in.readLine();
          if (line != null) {
            System.out.println("OK");
            System.out.println("rank is "+line);
            start_point = System.currentTimeMillis();
          } else {
            System.out.println("FALSE RETRY!");
          }
          
  
          // 以下breakせずに2回目答えようとすると
          // ２番目のクライアントがreadlineできなくなる。

          // line = in.readLine();
          // System.out.println("rank is"+line);
          
        }
        else{
          System.out.println("Wrong answer");
        }
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