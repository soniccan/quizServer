package quizserver;
import java.io.PrintWriter;

public class Rank {
    private int rank=0;

    public synchronized int send(PrintWriter out)
    {
        rank++;
        out.println(rank);
        return rank;
    }
    public synchronized boolean fin_q(int max)
    {
        return max < rank;
    }



    

}
