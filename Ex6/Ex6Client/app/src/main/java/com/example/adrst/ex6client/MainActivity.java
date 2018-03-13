package com.example.adrst.ex6client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ClientActivity";
    private TextView inputA, inputB;
    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputA = findViewById(R.id.inputA);
        inputB = findViewById(R.id.inputB);
        Log.d(TAG, "onCreate: starting client");
    }

    public void onClickSend(View view) {
        Log.d(TAG, "onClickSend: setting up question");
        client.sendQuestion(
                Integer.parseInt(inputA.getText().toString()),
                Integer.parseInt(inputB.getText().toString())
        );
    }

    public void onClickStartClient(View view) {
        TextView answer = findViewById(R.id.answer);
        client = new Client(new WeakReference<TextView>(answer));
        client.start();
    }

    public void onClickStopClient(View view) {
        Log.d(TAG, "onClickStopClient: called");
        client.stopClient();
    }
}
