package com.example.adrst.randomtoast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int upperLimit = 100;
        Intent intent = new Intent("com.example.adrst.randomtoast.NewActivity");
        intent.putExtra("Upper limit", upperLimit);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            TextView textView = findViewById(R.id.textView_result);
            String text = "Random number from other activity: " + Integer.toString(data.getIntExtra("randomNumber", 1));
            textView.setText(text);
        }
    }
}
