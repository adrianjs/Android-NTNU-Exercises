package com.example.adrst.calculationtester;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView num1, num2;
    EditText userAns, userBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        userAns = findViewById(R.id.userAns);
        userBound = findViewById(R.id.userBound);
    }

    /**
     * Function for add button. Checks to see if num1 + num2 = input
     * Shows a Toast either way, but a Toast saying correct if correct,
     * and a toast saying wrong and the correct answer if wrong.
     * @param v - The view currently occupying the screen
     */
    public void onClickAdd(View v) {
        int number1 = Integer.parseInt(num1.getText().toString());
        int number2 = Integer.parseInt(num2.getText().toString());
        int sum = number1 + number2;
        int input = Integer.parseInt(userAns.getText().toString());
        if (number1 + number2 == input) {
            Toast.makeText(this, R.string.correct, Toast.LENGTH_LONG).show();
            setNewNumbers();
        } else {
            Toast.makeText(this, getText(R.string.wrong) + " " + sum , Toast.LENGTH_LONG).show();
            setNewNumbers();
        }
    }

    /**
     * Function for multiply button. Checks to see if num1 * num2 = input.
     * Shows a Toast either way, but a Toast saying correct if correct,
     * and a toast saying wrong and the correct answer if wrong.
     * @param v - The view currently occupying the screen.
     */
    public void onClickMultiply(View v) {
        int number1 = Integer.parseInt(num1.getText().toString());
        int number2 = Integer.parseInt(num2.getText().toString());
        int product = number1 * number2;
        int input = Integer.parseInt(userAns.getText().toString());
        if (number1 * number2 == input) {
            Toast.makeText(this, R.string.correct, Toast.LENGTH_LONG).show();
            setNewNumbers();
        } else {
            Toast.makeText(this, getText(R.string.wrong) + " " + product, Toast.LENGTH_LONG).show();
            setNewNumbers();
        }
    }

    /**
     * Function for randomly setting new numbers to add or multiply.
     * Takes in the user-chosen upper bound for the numbers to get a range for the number.
     */
    public void setNewNumbers() {
        int upperBound = Integer.parseInt(userBound.getText().toString());
        Random rand = new Random();
        num1.setText(String.valueOf(rand.nextInt(upperBound)));
        num2.setText(String.valueOf(rand.nextInt(upperBound)));
    }
}
