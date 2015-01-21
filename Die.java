package huka.com.greed;

import android.app.Activity;
import android.widget.ImageView;

import java.util.ArrayList;

public class Die {

    Activity activity;
    ImageView die1;
    ImageView die2;
    ImageView die3;
    ImageView die4;
    ImageView die5;
    ImageView die6;

    ArrayList<ImageView> dieViews = new ArrayList<ImageView>();
    int[] dieImages = new int[] {R.drawable.white1, R.drawable.white2, R.drawable.white3, R.drawable.white4, R.drawable.white5, R.drawable.white6};
    int[] dieInactiveImages = new int[] {R.drawable.grey1, R.drawable.grey2, R.drawable.grey3, R.drawable.grey4, R.drawable.grey5, R.drawable.grey6};
    ArrayList<Integer> values = new ArrayList<Integer>();

    public Die(Activity activity) {
        this.activity = activity;
        die1 = (ImageView) activity.findViewById(R.id.die1);
        die2 = (ImageView) activity.findViewById(R.id.die2);
        die3 = (ImageView) activity.findViewById(R.id.die3);
        die4 = (ImageView) activity.findViewById(R.id.die4);
        die5 = (ImageView) activity.findViewById(R.id.die5);
        die6 = (ImageView) activity.findViewById(R.id.die6);
        die1.setActivated(false);
        die2.setActivated(false);
        die3.setActivated(false);
        die4.setActivated(false);
        die5.setActivated(false);
        die6.setActivated(false);
        dieViews.add(die1);
        dieViews.add(die2);
        dieViews.add(die3);
        dieViews.add(die4);
        dieViews.add(die5);
        dieViews.add(die6);
    }

    public ArrayList<Integer> roll() {
        values.clear();
        for (ImageView dieView : dieViews) {
            if (!dieView.isActivated()) {
                int dieValue = (int) Math.round((Math.random() * (dieImages.length - 1)));
                dieView.setImageResource(dieImages[dieValue]);
                values.add(dieValue + 1);
            }
        }
        return values;
    }

    // TODO: fix toggling images if some dices already toggled
    public void toggle(int die) {
        if(dieViews.get(die).isActivated()) {
            dieViews.get(die).setImageResource(dieImages[values.get(die) - 1]);
            dieViews.get(die).setActivated(false);
        } else {
            dieViews.get(die).setImageResource(dieInactiveImages[values.get(die) - 1]);
            dieViews.get(die).setActivated(true);
        }
    }

    public void resetDices() {
        int i = 0;
        for (ImageView dieView : dieViews) {
            if(dieView.isActivated()) {
                dieView.setImageResource(dieImages[values.get(i - 1)]);
                dieView.setActivated(false);
                i++;
            }
        }
    }
}
