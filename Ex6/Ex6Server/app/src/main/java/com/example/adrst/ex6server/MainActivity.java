package com.example.adrst.ex6server;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "ServerActivity";
    private Server server;
    TextView statusField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        statusField = findViewById(R.id.statusView);
    }

    public void onClickStart(View view) {
        try {
            server.stopServer();
        } catch (Exception e) {
            Log.e(TAG, "onClickStart: " + e.getMessage());
        }
        server = new Server(new WeakReference<>(statusField));
        server.start();
    }

    public void onClickStop(View view) {
        server.stopServer();
    }
}
