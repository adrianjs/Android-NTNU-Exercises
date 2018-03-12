package com.example.adrst.randomtoast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        int upperLimit = getIntent().getIntExtra("Upper limit", 100);
        TextView textView = findViewById(R.id.textView_upperlimit);
        String viewString = String.format(getResources().getString(R.string.upper_limit), upperLimit);
        textView.setText(viewString);

        int randomNumber = new Random().nextInt(upperLimit);
        /*String toastText = "Random number generated: " + randomNumber;
        Toast.makeText(this, toastText, Toast.LENGTH_LONG).show();*/

        Intent intent = new Intent();
        intent.putExtra("randomNumber", randomNumber);
        setResult(RESULT_OK, intent);
        finish();
    }
}
