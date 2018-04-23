package pegsolitairecom.adrst.pegsolitaire;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void onClickNorway(View view) {
        Locale locale = new Locale("nb", "NO");
        Configuration config = new Configuration();
        config.setLocale(locale);
        Resources res = getBaseContext().getResources();
        res.updateConfiguration(config, res.getDisplayMetrics());
    }

    public void onClickUK(View view) {
        Locale locale = new Locale("en", "GB");
        Configuration config = new Configuration();
        config.setLocale(locale);
        Resources res = getBaseContext().getResources();
        res.updateConfiguration(config, res.getDisplayMetrics());
    }
}
