package quizserver;



class ParseReceiveMessage
{

    public static String Parse(String line) {
        // プロトコルをIPAddress + "#" + "Message"とする。
        


        String ipAddress = line.substring(0,49);
        String message = line.substring(50,line.length());
        StringBuilder sb = new StringBuilder();

        sb.append(ipAddress);
        sb.append("#");
        sb.append(message);

        System.out.println(ipAddress);
        System.out.println(message);
        
        

        return "";
    }

    public static Object Response(String[] messageParsed) {
        return null;
    }
    
}