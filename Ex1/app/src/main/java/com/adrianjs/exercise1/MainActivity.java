package com.adrianjs.exercise1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button firstName, lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstName = findViewById(R.id.btnFN);
        lastName = findViewById(R.id.btnLN);
        firstName.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d("First Name", "Adrian");
            }
        });
        lastName.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.d("Last Name", "Steffenakk");
            }
        });
    }
}
