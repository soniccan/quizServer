package quizserver;



class ParseReceiveMessage
{

    public static String[] Parse(String line) {
        // プロトコルをIPAddress + "#" + "Message"とする。
        


        String ipAddress = line.substring(0,48);
        String message = line.substring(50,line.length());
        StringBuilder sb = new StringBuilder();

        sb.append(ipAddress);
        sb.append("#");
        sb.append(message);

        System.out.println(ipAddress);
        System.out.println(message);
        
        String[] parsed = new String[2];

        parsed[0] = ipAddress;
        parsed[1] = message;

        return parsed;
    }
    
}