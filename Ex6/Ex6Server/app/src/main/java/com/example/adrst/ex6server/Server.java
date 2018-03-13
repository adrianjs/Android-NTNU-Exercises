package com.example.adrst.ex6server;

import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by adrst on 13.03.2018.
 */

public class Server extends Thread {
    private final static String TAG = "Server";
    private final static int PORT = 12345;
    private final AtomicReference<Thread> currentThread = new AtomicReference<>();
    private WeakReference<TextView> statusField;

    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private PrintWriter printWriter = null;
    private BufferedReader bufferedReader = null;

    Server(WeakReference<TextView> statusField) {
        this.statusField = statusField;
    }

    @Override
    public void run() {
        currentThread.set(Thread.currentThread());

        try {
            log("Starting server..");
            serverSocket = new ServerSocket(PORT);
            log("ServerSocket created, waiting for client.");
            while (!Thread.currentThread().isInterrupted()) {
                socket = serverSocket.accept();
                log("Client connected.");
                printWriter = new PrintWriter(socket.getOutputStream(), true);
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                log("run: Sending welcome message");
                printWriter.println("Welcome, client!");

                while (true) {
                    if (bufferedReader.ready()) {
                        log("Listening for client input.");
                        String request = bufferedReader.readLine();
                        log("Message from client: " + request);
                        if (request == null) {
                            continue;
                        }
                        if (request.contains("q")) {
                            break;
                        }
                        String[] numbers = request.split(",");
                        int response = Integer.parseInt(numbers[0]) + Integer.parseInt(numbers[1]);
                        log("Returning to client: " + response);
                        printWriter.println(response);
                    }
                }
                log("Client disconnected.");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            closeAll();
        }
    }

    private void closeAll() {
        try {
            log("Closing");
            printWriter.close();
            bufferedReader.close();
            socket.close();
            serverSocket.close();
            log("Closed");
        } catch (Exception e) {
            log("Exception: " + e.getMessage());
        }
    }

    void stopServer() {
        log("Stopping");
        closeAll();
        currentThread.get().interrupt();
        log("Stopped");
    }

    private void log(final String msg) {
        statusField.get().post(new Runnable() {
            @Override
            public void run() {
                statusField.get().setText(msg);
            }
        });
        Log.d(TAG, msg);
    }
}
