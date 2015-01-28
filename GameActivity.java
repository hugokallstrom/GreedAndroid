package huka.com.greed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GameActivity extends Activity {

    public final static String EXTRA_MESSAGE = "huka.com.greed.MESSAGE";
    Dice dice;
    Score score;
    private boolean sameRound;
    private int turnResult;
    private int rounds;
    private int totalResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        dice = new Dice(this);
        score = new Score(this);
        dice.roll();
    }

    public void ButtonOnClick(View v) {
        switch (v.getId()) {
            case R.id.throwButton:
                rollDice();
                break;
            case R.id.scoreButton:
                saveScore();
                break;
            case R.id.die1:
                toggle(0);
                break;
            case R.id.die2:
                toggle(1);
                break;
            case R.id.die3:
                toggle(2);
                break;
            case R.id.die4:
                toggle(3);
                break;
            case R.id.die5:
                toggle(4);
                break;
            case R.id.die6:
                toggle(5);
                break;
        }
    }

    private void rollDice() {
        ArrayList<Integer> diceValues = dice.roll();
        int calculatedScore = score.calc(diceValues);

        if(score.allScores()) {
            sameRound = true;
            continueRound(calculatedScore);
            dice.resetDice();
            return;
        }

        int playableDice = dice.getPlayable();
        if(validThrow(calculatedScore, playableDice)) {
            sameRound = false;
            continueRound(calculatedScore);
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

    public void continueRound(int tempResult) {
        turnResult += tempResult;
        TextView turnScore = (TextView) findViewById(R.id.turnScore);
        turnScore.setText(String.valueOf(turnResult));
    }

    private void resetRound() {
        TextView turnScore = (TextView) findViewById(R.id.turnScore);
        turnScore.setText(String.valueOf(0));
        dice.resetDice();
        turnResult = 0;
        sameRound = false;
        rounds++;
    }

    public void saveScore() {
        if(totalResult + turnResult >= 1000) {
            toWinActivity();
        } else if(turnResult >= 300) {
            dice.resetDice();
            rounds++;
            totalResult += turnResult;
            turnResult = 0;
            changeScoreText();
        }
    }

    private void toWinActivity() {
        Intent intent = new Intent(this, GameWonActivity.class);
        intent.putExtra(EXTRA_MESSAGE, rounds);
        startActivity(intent);
    }

    private void changeScoreText() {
        TextView currentScore = (TextView) findViewById(R.id.currentScore);
        TextView turnScore = (TextView) findViewById(R.id.turnScore);
        currentScore.setText(String.valueOf(totalResult));
        turnScore.setText(String.valueOf(0));
    }

    public void toggle(int index) {
        if((totalResult >= 300 && turnResult == 0) || (turnResult > 0)) dice.toggle(index);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        ArrayList<Integer> diceTags = new ArrayList<Integer>();
        boolean[] diceActivated = new boolean[6];
        ArrayList<ImageView> dieViews = dice.getDieViews();
        for (int i = 0; i < 6; i++) {
            diceTags.add((Integer)dieViews.get(i).getTag());
            diceActivated[i] = dieViews.get(i).isActivated();
            Log.v("active", String.valueOf(dieViews.get(i).isActivated()));
        }
        savedInstanceState.putBoolean("sameRound", sameRound);
        savedInstanceState.putInt("turnResult", turnResult);
        savedInstanceState.putInt("totalResult", totalResult);
        Log.v("tags", diceTags.toString());
        savedInstanceState.putInt("rounds", rounds);
        savedInstanceState.putBooleanArray("activeDice", diceActivated);
        savedInstanceState.putIntegerArrayList("diceTags", diceTags);
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        ArrayList<Integer> diceTags;
        boolean[] diceActivated;
        super.onRestoreInstanceState(savedInstanceState);
        sameRound = savedInstanceState.getBoolean("sameRound");
        turnResult = savedInstanceState.getInt("turnResult");
        totalResult = savedInstanceState.getInt("totalResult");
        rounds = savedInstanceState.getInt("rounds");
        diceActivated = savedInstanceState.getBooleanArray("activeDice");
        diceTags = savedInstanceState.getIntegerArrayList("diceTags");
        TextView currentScore = (TextView) findViewById(R.id.currentScore);
        TextView turnScore = (TextView) findViewById(R.id.turnScore);
        currentScore.setText(String.valueOf(totalResult));
        turnScore.setText(String.valueOf(turnResult));
        dice.setDieViews(diceActivated, diceTags);
    }
}
