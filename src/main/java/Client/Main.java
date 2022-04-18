package Client;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class Main {
    private static Socket socketOfClient = null;
    private static  PrintWriter out = null;
    private static BufferedReader reader;
    private static BufferedReader in = null;
    private static int port = 8099;
    private static String outString = "";

    public static void main(String[] args) {
        // Server Host
        final String serverHost = "netology.homework";



        try {
            // Send a request to connect to the server is listening
            // on machine 'localhost' port 9999.
            socketOfClient = new Socket(serverHost, port);

            //Create output stream at the client (to send data to the server)
            // Оборачиваем в PrintWriter что бы не писать flush  в буффере
            // true выталкивание из буфера будет происходить автоматически
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream())), true);

            // Input stream at Client (Receive data from the server).
            in = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
            reader = new BufferedReader(new InputStreamReader(System.in));// read from console
            {
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + serverHost);
            return;
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + serverHost);
            return;
        }
        try {

            while (true) {
                outString = in.readLine();
                if (outString.contains("Welcome to the")){
                    System.out.println(outString);
                    break;
                }
                System.out.println(outString);
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
