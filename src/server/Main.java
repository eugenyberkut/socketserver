package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Eugeny on 18.03.2016.
 */
public class Main {
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            do {
                Socket socket = serverSocket.accept();
                new Thread(new ClientThread(socket, this)).start();
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
