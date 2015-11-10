import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by zhanyang on 15/10/11.
 */
public class Mancala {

    public static void main(String[] args) throws IOException {
        if (args[0].equals("calibrate")) {
            calibrate();
        } else if (args[0].equals("run")) {
            run();
        }
    }

    public static void calibrate() throws IOException {
        FileWriter fw = new FileWriter("speedTest.txt");
        fw.close();
        fw = new FileWriter("speedTest.txt", true);
        int depth = 1;
        while (depth < 15) {
            fw.write(getTestCaseRunTime(depth)+"\n");
            depth++;
        }
        fw.close();
    }

    public static long getTestCaseRunTime(int depth) {
        long start = System.currentTimeMillis();
        GameBoard gbinstance = new GameBoard("4 4 4 4 4 4", "4 4 4 4 4 4", "0", "0");
//        GameBoard gbinstance = new GameBoard("1000 1000 1000 1000 1000 1000", "1000 1000 1000 1000 1000 1000", "0", "0");
        Action rootact = new Action("root", gbinstance, false, true, 0);
        EvaluationFunc ev = new EvaluationFunc(true, EvaluationFunc.ALLDIFF);
        SearchStrategy search = new SearchStrategy(SearchStrategy.TRAVERSE);
        FactorEstimate fe = new FactorEstimate(200, gbinstance.getStonesNum(), gbinstance.getNonEmptyNum(true));
        int fakedepth = fe.estimateDepth();

        Action nextstate = new Alpha_Beta(search, ev, depth).decision(rootact);
        return System.currentTimeMillis() - start;
    }

    public static void run() throws IOException {
        // read the input file
        BufferedReader filein = new BufferedReader(new FileReader("input.txt"));

        // mode is always 4 for AI competition
        String mode = filein.readLine();

        boolean ifplayer1 = true;
        if (filein.readLine().equals("2")) {
            ifplayer1 = false;
        }

        float time = Float.parseFloat(filein.readLine()) * 1000;

        String board4player2 = filein.readLine();
        String board4player1 = filein.readLine();
        String mancala2 = filein.readLine();
        String mancala1 = filein.readLine();
        filein.close();
        GameBoard gbinstance = new GameBoard(board4player2, board4player1, mancala2, mancala1);

        Action rootact = new Action("root", gbinstance, false, ifplayer1, 0);

        FactorEstimate fe = new FactorEstimate(time, gbinstance.getStonesNum(), gbinstance.getNonEmptyNum(ifplayer1));
        int depth = fe.estimateDepth();

        EvaluationFunc ev = new EvaluationFunc(ifplayer1, EvaluationFunc.ALLDIFF);
        SearchStrategy search = new SearchStrategy(SearchStrategy.TRAVERSE);

        Action nextstate = null;
        nextstate = new Alpha_Beta(search, ev, depth).decision(rootact);
//        System.out.println(nextstate);

//        FileWriter fileout = new FileWriter("input.txt");
//        String move = String.format("4\n%d\n%d\n%s", (ifplayer1) ? 1 : 2, depth, nextstate.gb.toString());
//        System.out.println(move);
//        fileout.write(move);
//        fileout.close();

        FileWriter nstate = new FileWriter("output.txt");
        nstate.write(nextstate.toString());
        nstate.close();
    }
}
