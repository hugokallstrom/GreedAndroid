package huka.com.greed;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class GameActivity extends Activity {

    GameHandler gameHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameHandler = new GameHandler(this);
    }

    public void ButtonOnClick(View v) {
        switch (v.getId()) {
            case R.id.throwButton:
                gameHandler.throwDices();
                break;
            case R.id.scoreButton:
                gameHandler.saveScore();
                break;
            case R.id.saveButton:
                // add score of dieValues to Turn score
                break;
            case R.id.die1:
                gameHandler.toggle(0);
                break;
            case R.id.die2:
                gameHandler.toggle(1);
                break;
            case R.id.die3:
                gameHandler.toggle(2);
                break;
            case R.id.die4:
                gameHandler.toggle(3);
                break;
            case R.id.die5:
                gameHandler.toggle(4);
                break;
            case R.id.die6:
                gameHandler.toggle(5);
                break;

        }
    }


}
