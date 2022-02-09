package quizserver;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.lang.model.util.ElementScanner14;

public class ServerData {

    // リスト(Map)を作る。ipaddressをキーとして、現在のクイズ正答数|正しいを答えを返せるMap
    Map<String, Integer> map;
    String quizStatement[]  ={"1+1","2+2","3+3"};
    String ans[]  ={"2","4","6"};

    public ServerData(){
        this.map = new HashMap<>();
    }



    public String Parse(String line, ServerData server_date) {

        String id = line.substring(0,36);
        String message = line.substring(37,line.length());
        Integer quizNumberNow;
        String parsedMessage ="";



        quizNumberNow = this.map.get(id);
        if( quizNumberNow == null  )
        {
            this.map.put(id,0);
            quizNumberNow =0;
            parsedMessage = "You (" + message + ") join game\n";
            return  "You (" + message + ") join game\n";
        }

        if(quizNumberNow.equals(2))
        {
            return "You WIN!!!!!";
        }

        if(isCollectAnswer(message,quizNumberNow))
        {

            quizNumberNow = this.map.get(id);
            this.map.put(id,++quizNumberNow);

            parsedMessage = "You answer is collect next quiz number is (" + quizNumberNow + ")  \n";
        }
        else {
            parsedMessage = "You answer is not collect next quiz number is (" + quizNumberNow + ")  \n";
        }

        parsedMessage = parsedMessage + quizStatement[quizNumberNow] + "\n";



        return parsedMessage;


    }



    private boolean isCollectAnswer(String message, Integer quizNumberNow) {
        return ans[quizNumberNow].equals(message);
    }

}
