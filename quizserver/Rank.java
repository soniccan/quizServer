package quizserver;
import java.io.PrintWriter;

public class Rank {
    private int rank=0;

    public void send(PrintWriter out)
    {
        rank++;
        out.println(rank);
    }

}
