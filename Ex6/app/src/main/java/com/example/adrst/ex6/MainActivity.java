package com.example.adrst.ex6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements Client.OnCalculateNumbersListener {
    private Client client;
    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickRadioButtons(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radioClient:
                if (checked) {
                    Button buttonConnectServer = findViewById(R.id.btnConnectServer);
                    buttonConnectServer.setVisibility(View.VISIBLE);
                    Button buttonStartServer = findViewById(R.id.btnStartServer);
                    buttonStartServer.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.radioServer:
                if (checked) {
                    Button buttonConnectServer = findViewById(R.id.btnConnectServer);
                    buttonConnectServer.setVisibility(View.INVISIBLE);
                    Button buttonStartServer = findViewById(R.id.btnStartServer);
                    buttonStartServer.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    public void onClickStartServer(View view) {
        TextView applicationHelper = findViewById(R.id.applicationHelper);
        applicationHelper.setText("You are a server");
        Server server = new Server();
        server.start();
        Button startServerButton = findViewById(R.id.btnStartServer);
        startServerButton.setVisibility(View.INVISIBLE);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setVisibility(View.INVISIBLE);
    }

    public void onClickConnectServer(View view) {
        TextView applicationHelper = findViewById(R.id.applicationHelper);
        applicationHelper.setText("You are a client");
        LinearLayout layoutSumNumbers = findViewById(R.id.layoutSumNumbers);
        layoutSumNumbers.setVisibility(View.VISIBLE);
        Button connectServerButton = findViewById(R.id.btnConnectServer);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        connectServerButton.setVisibility(View.INVISIBLE);
        radioGroup.setVisibility(View.INVISIBLE);
    }

    public void onClickCalculate(View view) {
        EditText numberOneInput = findViewById(R.id.numberOneInput);
        EditText numberTwoInput = findViewById(R.id.numberTwoInput);
        int number1 = Integer.parseInt(numberOneInput.getText().toString());
        int number2 = Integer.parseInt(numberTwoInput.getText().toString());

        client = new Client(this, number1, number2);
        client.start();
    }

    @Override
    public void OnCalculateNumbersListener(final int result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                EditText numberOneInput = findViewById(R.id.numberOneInput);
                EditText numberTwoInput = findViewById(R.id.numberTwoInput);
                int number1 = Integer.parseInt(numberOneInput.getText().toString());
                int number2 = Integer.parseInt(numberTwoInput.getText().toString());
                TextView showSum = findViewById(R.id.showSum);
                showSum.setVisibility(View.VISIBLE);
                showSum.setText(String.format(Locale.ENGLISH,"The sum of %d and %d is %s",
                        number1, number2, Integer.toString(result)));
                Log.i(TAG, String.format("Sum of %d and %d is %s", number1, number2, Integer.toString(result)));
            }
        });
    }
}
