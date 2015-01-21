package huka.com.greed;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by hugo on 1/21/15.
 */
public class GameHandler {

    Activity activity;
    Die die;
    Score score;
    int rounds;
    int result;
    ArrayList<Integer> dieValues;

    public GameHandler(Activity activity) {
        this.activity = activity;
        die = new Die(activity);
        score = new Score(activity);
    }

    public void throwDices() {
        dieValues = die.roll();
        Log.v("dievals", String.valueOf(dieValues));
        int tempResult = score.calc(dieValues);
        Log.v("res", String.valueOf(tempResult));
        if(tempResult < 300) {
            die.resetDices();
        } else {
            result += tempResult;
        }
    }

    public void saveScore() {
        if(result >= 300) {
            die.resetDices();
            rounds++;
            Log.v("rounds", String.valueOf(rounds));
        }
    }

    public void toggle(int index) {
        if(result >= 300) die.toggle(index);
    }
}
