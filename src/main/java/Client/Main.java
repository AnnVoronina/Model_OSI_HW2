package Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class Main {
    private static int port = 8099;
    private static final String serverHost = "localhost";
    private static String outString;

    public static void main(String[] args) {
        try (var socketOfClient = new Socket(serverHost, port);
             //Create output stream at the client (to send data to the server)
             // Оборачиваем в PrintWriter что бы не писать flush  в буффере
             // true выталкивание из буфера будет происходить автоматически
             var out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream())), true);
             // Input stream at Client (Receive data from the server).
             var in = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
             var reader = new BufferedReader(new InputStreamReader(System.in))// read from console
        ) {
            boolean isEnd = false;
            while (!isEnd) {
                while (in.ready()) {
                    String s = in.readLine();
                    if (s.equals("end")) {
                        isEnd = true;
                        break;
                    }
                    System.out.println(s);
                }
                String word = reader.readLine();
                out.println(word);
            }

        } catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host: " + e);
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
    }
}
