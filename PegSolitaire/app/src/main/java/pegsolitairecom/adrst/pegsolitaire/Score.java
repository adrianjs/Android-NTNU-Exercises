package pegsolitairecom.adrst.pegsolitaire;

import android.support.annotation.NonNull;

public class Score implements Comparable<Score> {

    private String scoreDate;
    public int scoreNum;

    public Score(String date, int num) {
        scoreDate = date;
        scoreNum = num;
    }

    @Override
    public int compareTo(@NonNull Score score) {
        // return 0 if equal
        // 1 if passed greater than this
        // -1 if this greater than passed
        return score.scoreNum > scoreNum ? 1 : score.scoreNum < scoreNum ? -1 : 0;
    }

    public String getScoreText() {
        return scoreDate + " - " + scoreNum;
    }
}
