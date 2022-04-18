package Server1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


//отослать что-то с помощью in, и принять с помощью out
public class Main {

    public static void main(String[] args) {

        ServerSocket listener = null;
        BufferedReader in;
        PrintWriter out;
        int port = 8099;
        Socket socketOfServer = null;

        try {
            listener = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }

        try {
            System.out.println("Server is waiting to accept user...");
            // Accept client connection request
            // Get new Socket at Server.
            socketOfServer = listener.accept();
            System.out.println("Accept a client!");

            // Open input and output streams
            in = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream())), true);

            out.println("Write your name!");
            String name = in.readLine();
            out.println("Are you child? (yes/no)");
            String ageAnswer = in.readLine();
            while (true) {
                if (ageAnswer.equals("no") || ageAnswer.equals("yes")) {
                    if (ageAnswer.equals("yes")) {
                        out.println("Welcome to the kids area, " + name + "! Let's play!");
                        break;
                    } else out.println("Welcome to the adult zone, " + name + "! Let's drink!");
                    break;
                } else {
                    out.println("Are you child? Enter yes or no");
                   ageAnswer = in.readLine();
                }
            }
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        System.out.println("Sever stopped!");
    }
}

