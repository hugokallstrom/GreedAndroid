package huka.com.greed;

import android.app.Activity;

import java.util.ArrayList;

public class Score {

    Activity activity;
    ArrayList<Integer> values = new ArrayList<Integer>();
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

    private boolean isStraight() {
        if(values.size() != 6) return false;
        for(int i = 0; i < 6; i++) {
            for(int j = i + 1; j < 6; j++) {
                if(values.get(i).equals(values.get(j))) return false;
            }
        }
        values.clear();
        return true;
    }

    private int calcThreeOfAKind() {
        if(values.size() < 3) return 0;
        for(int i = 0; i < values.size(); i++) {
            for(int j = i + 1; j < values.size(); j++) {
                for(int k = j + 1; k < values.size(); k++) {
                    if(values.get(i).equals(values.get(j)) && values.get(k).equals(values.get(j))) {
                        Integer threeValue = values.get(i);
                        values.remove(threeValue);
                        values.remove(threeValue);
                        values.remove(threeValue);
                        if(threeValue.equals(1)) return 1000;
                        return threeValue*100;
                    }
                }
            }
        }
        return 0;
    }

    private int calcSingles() {
        int score = 0;
        int fives = 0;
        int ones = 0;
        for (Integer value : values) {
            if(value.equals(5)) {
                score += 50;
                fives++;
            }
            if(value.equals(1)) {
                score += 100;
                ones++;
            }
        }
        removeSingles(fives, ones);
        return score;
    }

    private void removeSingles(int fives, int ones) {
        Integer five = 5;
        Integer one = 1;
        for(int i = 0; i < fives; i++) {
            values.remove(five);
        }
        for(int i = 0; i < ones; i++) {
            values.remove(one);
        }
    }

    public boolean allScores() {
        return values.isEmpty();
    }
}
