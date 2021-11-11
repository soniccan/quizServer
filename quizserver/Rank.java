package quizserver;
import java.io.PrintWriter;

public class Rank {
    private int rank = 0;
    private short p_num = 0;

    public synchronized int send(PrintWriter out)
    {
        rank++;
        // out.println(rank);
        return rank;
    }
    public synchronized boolean fin_q(int max)
    {
        return max == rank;
    }
    public synchronized short player_inc()
    {
        p_num++;
        return p_num;
    }

}
