package com.example.adrst.ex6client;

import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by adrst on 13.03.2018.
 */

public class Client extends Thread {
    private final static String TAG = "Client";
    private final static String IP = "localhost";
    private final static int PORT = 12345;
    private WeakReference<TextView> answerView;
    private Socket socket = null;
    private PrintWriter printWriter = null;
    private final AtomicReference<Thread> currentThread = new AtomicReference<>();

    public Client(WeakReference<TextView> answerView) {
        this.answerView = answerView;
    }

    @Override
    public void run() {
        currentThread.set(Thread.currentThread());
        BufferedReader bufferedReader = null;

        try {
            socket = new Socket(IP, PORT);
            log("run: C: Connected to server: " + socket.toString());
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (!Thread.currentThread().isInterrupted()) {
                if (bufferedReader.ready()) {
                    String result = bufferedReader.readLine();
                    log(String.valueOf(result));
                }
            }
            log("run:  finished, stopping naturally");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
                printWriter.close();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sendQuestion(int a, int b) {
        if (printWriter == null) {
            log("sendQuestion: Error, connection not started");
        }
        final String request = a + "," + b;
        new Thread(new Runnable() {
            @Override
            public void run() {
                printWriter.println(request);
            }
        }).start();
    }

    public void stopClient() {
        new Thread() {
            @Override
            public void run() {
                printWriter.println("q");
                log("stopServer: Stopping");
                try {
                    socket.close();
                } catch (Exception e) {
                    log("stopServer: " + e.getMessage());
                }
                currentThread.get().interrupt();
                log("stopServer: stopped");
            }
        }.start();
    }

    private void log(final String msg) {
        answerView.get().post(new Runnable() {
            @Override
            public void run() {
                answerView.get().setText(msg);
            }
        });
        Log.d(TAG, msg);
    }
}
