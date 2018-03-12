package com.example.adrst.ex5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private HttpConnection httpConnection;
    private boolean isStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        httpConnection = new HttpConnection(this);
    }

    public void displayResponse(String response) {
        String TAG = "MainActivity";
        Log.i(TAG, response);
        if (response.contains("Oppgi et tall mellom ")) {
            // First attempt
            TextView reply = findViewById(R.id.numberHelper);
            reply.setText(response);
            reply.setVisibility(View.VISIBLE);
            EditText numberInput = findViewById(R.id.numberInput);
            numberInput.setVisibility(View.VISIBLE);

            RelativeLayout relativeLayout = findViewById(R.id.relativeLayout);
            relativeLayout.setVisibility(View.INVISIBLE);
        } else if (response.contains("du må starte på nytt")) {
            // All attempts have been used
            isStarted = false;
            TextView reply = findViewById(R.id.numberHelper);
            reply.setText(response);
            EditText numberInput = findViewById(R.id.numberInput);
            numberInput.setVisibility(View.INVISIBLE);

            RelativeLayout relativeLayout = findViewById(R.id.relativeLayout);
            relativeLayout.setVisibility(View.VISIBLE);
        } else if (response.contains("du har vunnet")) {
            // User won
            TextView reply = findViewById(R.id.numberHelper);
            reply.setText(response);
            Button sendBtn = findViewById(R.id.btnSend);
            sendBtn.setVisibility(View.INVISIBLE);
            EditText numberInput = findViewById(R.id.numberInput);
            numberInput.setVisibility(View.INVISIBLE);
        } else {
            // Other attempts
            TextView reply = findViewById(R.id.numberHelper);
            reply.setText(response);
        }
    }

    private void startNewGame() {
        isStarted = false;
        TextView reply = findViewById(R.id.numberHelper);
        EditText numberInput = findViewById(R.id.numberInput);
        reply.setVisibility(View.INVISIBLE);
        numberInput.setVisibility(View.INVISIBLE);

        RelativeLayout relativeLayout = findViewById(R.id.relativeLayout);
        relativeLayout.setVisibility(View.VISIBLE);

        Button sendBtn = findViewById(R.id.btnSend);
        sendBtn.setVisibility(View.VISIBLE);
    }

    public void onClickSend(View v) {
        if (!isStarted) {
            isStarted = true;
            EditText nameInput = findViewById(R.id.nameInput);
            EditText cardInput = findViewById(R.id.cardInput);

            Map<String, String> parameters = new HashMap<>();
            parameters.put("navn", nameInput.getText().toString());
            parameters.put("kortnummer", cardInput.getText().toString());
            httpConnection.startNewThread(parameters);
            TextView reply = findViewById(R.id.numberHelper);
            reply.setVisibility(View.INVISIBLE);
        } else {
            EditText numberInput = findViewById(R.id.numberInput);
            Map<String, String> parameters = new HashMap<>();
            parameters.put("tall", numberInput.getText().toString());
            httpConnection.startNewThread(parameters);
        }
    }

    public void onClickStartNew(View v) {
        startNewGame();
    }
}
