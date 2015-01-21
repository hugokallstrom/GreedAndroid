package huka.com.greed;

import android.app.Activity;

import java.util.ArrayList;

public class Score {

    Activity activity;
    ArrayList<Integer> values;
    public Score(Activity activity) {
        this.activity = activity;
    }

    public int calc(ArrayList<Integer> values) {
        this.values = values;
        if(isStraight()) return 1000;
        int totalScore = calcThreeOfAKind();
        totalScore += calcThreeOfAKind();
        totalScore += calcSingles();
        return totalScore;
    }

    private int calcSingles() {
        int score = 0;
        for (Integer value : values) {
            if(value.equals(5)) score += 50;
            if(value.equals(1)) score += 100;
        }
        return score;
    }

    private int calcThreeOfAKind() {
        if(values.size() < 3) return 0;
        for(int i = 0; i < values.size(); i++) {
            for(int j = i + 1; j < values.size() - 1; j++) {
                for(int k = j + 1; k < values.size() - 2; k++) {
                    if(values.get(i).equals(values.get(j)) && values.get(k).equals(values.get(j))) {
                        values.remove(i);
                        values.remove(j);
                        values.remove(k);
                        if(values.get(i).equals(1)) return 1000;
                        return values.get(i)*100;
                    }
                }
            }
        }
        return 0;
    }

    private boolean isStraight() {
        if(values.size() != 6) return false;
        for(int i = 0; i < 6; i++) {
            for(int j = i + 1; j < 5; j++) {
                if(values.get(i).equals(values.get(j))) return false;
            }
        }
        return true;
    }
}
