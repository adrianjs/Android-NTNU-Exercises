package pegsolitairecom.adrst.pegsolitaire;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    public static final String TAG = "SettingsActivity";

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
        Toast.makeText(this, "Språk endret til norsk", Toast.LENGTH_LONG).show();
        Log.i(TAG, "onClickNorway: Endret til norsk språk");
    }

    public void onClickUK(View view) {
        Locale locale = new Locale("en", "GB");
        Configuration config = new Configuration();
        config.setLocale(locale);
        Resources res = getBaseContext().getResources();
        res.updateConfiguration(config, res.getDisplayMetrics());
        Toast.makeText(this, "Language changed to English", Toast.LENGTH_LONG).show();
        Log.i(TAG, "onClickUK: Language changed to English");
    }
}
