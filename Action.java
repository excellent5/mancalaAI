/**
 * Created by zhanyang on 15/10/11.
 */
public class Action {
    // the pit name of action
    String turn;
    // the gameboard state after taking this action
    GameBoard gb;
    // if after taking this action will cause an extra freeturn
    boolean freeturn;
    // who take turn after this action
    boolean ifplayer1;
    String path;

    int depth;
    int value;

    public Action(String turn, GameBoard gb, boolean freeturn, boolean ifplayer1, int depth) {
        this.turn = turn;
        this.gb = gb;
        this.freeturn = freeturn;
        this.ifplayer1 = ifplayer1;
        this.depth = depth;
        this.path = turn;
    }


    public Action(GameBoard gb, int value, String path) {
        this.gb = gb;
        this.value = value;
        this.path = path;
    }

    @Override
    public String toString() {
        if (path.length() < 5) {
            return "B2";
        }
        return path.substring(5);
    }
}
