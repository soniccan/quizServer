package quizserver;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.lang.model.util.ElementScanner14;

public class ServerData {

    // リスト(Map)を作る。ipaddressをキーとして、現在のクイズ正答数|正しいを答えを返せるMap
    Map<String, Integer> map;
    String quizstatement[]  ={"1+1","2+2","3+3"};
    String ans[]  ={"2","4","6"};

    public ServerData(){
        this.map = new HashMap<>();
    }
    
    

    public String Parse(String line, ServerData server_date) {

        String id = line.substring(0,36);
        String message = line.substring(37,line.length());
        Integer quiz_number_now;
        String parsedMessage ="";
        
        

        if( (quiz_number_now = this.map.get(id)) == null  )
        {  
            this.map.put(id,0);
            quiz_number_now =0;
            parsedMessage = "You (" + message + ") join game\n";
        }
        else if (isCollectAnswer(message,quiz_number_now))
        {  

            quiz_number_now = this.map.get(id);
            this.map.put(id,++quiz_number_now);

            parsedMessage = "You anwer is collect next quiz number is (" + quiz_number_now + ")  \n"; 
        }
        else {
            parsedMessage = "You anwer is not collect next quiz number is (" + quiz_number_now + ")  \n"; 
        }
        

        parsedMessage = parsedMessage + quizstatement[quiz_number_now] + "\n";
        
        return parsedMessage;


    }



    private boolean isCollectAnswer(String message, Integer quiz_number_now) {
        return ans[quiz_number_now].equals(message);
    }

    





    


}
