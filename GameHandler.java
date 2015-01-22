package huka.com.greed;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hugo on 1/21/15.
 */
public class GameHandler {

    Activity activity;
    Dice dice;
    Score score;
    int rounds;
    int turnResult;
    int totalResult;
    boolean sameRound = false;

    public GameHandler(Activity activity) {
        this.activity = activity;
        dice = new Dice(activity);
        score = new Score(activity);
    }

    public void throwDice() {
        ArrayList<Integer> diceValues = dice.roll();
        Log.v("rounds", String.valueOf(rounds));
        int tempResult = score.calc(diceValues);
        if(score.allScores()) {
            sameRound = true;
            continueRound(tempResult);
            dice.resetDice();
            return;
        }

        int playableDice = dice.getPlayable();
        if(validThrow(tempResult, playableDice)) {
            continueRound(tempResult);
        } else {
            resetRound();
        }
    }

    private boolean validThrow(Integer result, Integer playableDice) {
        if((result < 300 && turnResult == 0) || (turnResult > 0 && playableDice == 6 && !sameRound) || (result < 50 && playableDice < 6)) {
            return false;
        } else if(result >= 300 || (result > 0 && playableDice < 6) || (sameRound && result > 0)) {
            return true;
        }
        return false;
    }

    private void continueRound(int tempResult) {
        turnResult += tempResult;
        TextView turnScore = (TextView) activity.findViewById(R.id.turnScore);
        turnScore.setText(String.valueOf(turnResult));
    }

    private void resetRound() {
        TextView turnScore = (TextView) activity.findViewById(R.id.turnScore);
        turnScore.setText(String.valueOf(0));
        dice.resetDice();
        turnResult = 0;
        sameRound = false;
        rounds++;
    }

    public void saveScore() {
        if(totalResult + turnResult >= 10000) {
          Log.v("WIN", "Win");
        } else if(turnResult >= 300) {
            dice.resetDice();
            rounds++;
            totalResult += turnResult;
            turnResult = 0;
            TextView currentScore = (TextView) activity.findViewById(R.id.currentScore);
            TextView turnScore = (TextView) activity.findViewById(R.id.turnScore);
            currentScore.setText(String.valueOf(totalResult));
            turnScore.setText(String.valueOf(0));
        }
    }

    public void toggle(int index) {
        if((totalResult >= 300 && turnResult == 0) || (turnResult > 0)) dice.toggle(index);
    }

    public int getTotalRestult() {
        return totalResult;
    }
}
