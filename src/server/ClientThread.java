package server;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Eugeny on 18.03.2016.
 */
public class ClientThread implements Runnable {
    private Socket socket;
    Main main;

    public ClientThread(Socket s, Main main) {
        this.socket = s;
        this.main = main;
    }

    @Override
    public void run() {
        try {
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter out = new PrintWriter(new OutputStreamWriter(outputStream), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println("Hello from server");
            LocalDateTime dateTime = LocalDateTime.now().plusMonths(1).minusDays(3);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            out.println(formatter.format(dateTime));
            String line = new StringBuilder(in.readLine()).reverse().toString();
            out.println(line);
            out.close();
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
