package huka.com.greed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;


public class GameWonActivity extends Activity {

    int rounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_won);
        Intent intent = getIntent();
        rounds = intent.getIntExtra(GameActivity.EXTRA_MESSAGE, 42);
        TextView textView = (TextView) findViewById(R.id.roundsText);
        textView.setText(String.valueOf(rounds));
    }
}
