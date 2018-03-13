package com.example.adrst.ex6;

import android.util.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by adrst on 13.03.2018.
 */

public class Server extends Thread {
    private final static String TAG = "Server";
    private final static int PORT = 1337;

    @Override
    public void run() {
        Socket socket = null;
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(PORT);
            Log.i(TAG, "Server started");

            while (true) {
                socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                clientHandler.start();
                Log.i(TAG, "New client: " + socket.toString());
            }
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }

                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }
}
