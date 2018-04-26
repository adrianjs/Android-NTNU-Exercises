package pegsolitairecom.adrst.pegsolitaire;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        TextView scoreView = findViewById(R.id.score_list);
        SharedPreferences scorePrefs =  getSharedPreferences(GameActivity.GAME_PREFS, 0);
        String[] savedScores = scorePrefs.getString("highScores", "").split("\\|");
        StringBuilder scoreBuild = new StringBuilder("");
        for (String score : savedScores) {
            scoreBuild.append(score).append("\n");
        }
        scoreView.setText(scoreBuild.toString());
    }
}
