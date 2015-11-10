/**
 * Created by zhanyang on 15/10/11.
 */
public class EvaluationFunc {

    final static int MANCALADIFF = 0;
    final static int ALLDIFF = 1;

    boolean ifplayer1;
    int strategy = 0;

    public EvaluationFunc(boolean ifplayer1, int strategy) {
        this.ifplayer1 = ifplayer1;
        this.strategy = strategy;
    }

    public void evaluate(Action act) {
        if (strategy == 0) {
            act.value = getMancalaDiff(act.gb);
        } else if (strategy == 1) {
            act.value = getAlldiff(act.gb);
        }
    }

    public int getAlldiff(GameBoard gb) {
        int sign = ifplayer1 ? 1 : -1;
        int diff = 0;
        for (int i = 0; i < gb.boards4B.length; i++) {
            diff += (gb.boards4B[i] - gb.boards4A[i]);
        }
        return sign * diff;
    }


    public int getMancalaDiff(GameBoard gb) {
        int sign = ifplayer1 ? 1 : -1;
        return sign * (gb.boards4B[gb.boards4B.length - 1] - gb.boards4A[gb.boards4A.length - 1]);
    }
}
