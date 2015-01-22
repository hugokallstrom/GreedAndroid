package huka.com.greed;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
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
                gameHandler.throwDice();
                break;
            case R.id.scoreButton:
                gameHandler.saveScore();
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

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean("MyBoolean", true);
        savedInstanceState.putDouble("myDouble", 1.9);
        savedInstanceState.putInt("MyInt", 1);
        savedInstanceState.putString("MyString", "Welcome back to Android");
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.v("test", String.valueOf(gameHandler.getTotalRestult()));
    }
}
