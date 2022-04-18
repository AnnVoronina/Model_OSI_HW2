package Server1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;


//отослать что-то с помощью in, и принять с помощью out
public class Main {

    public static void main(String[] args) {

        int port = 8099;

        System.out.println("Server is waiting to accept user...");
        try (var listener = new ServerSocket(port);
             // Accept client connection request
             // Get new Socket at Server.
             var socketOfServer = listener.accept();
             var in = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
             var out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream())), true)) {

            while (true) {
                out.println("Write your name!");
                final String name = in.readLine();
                out.println("Are you child? (yes/no)");
                String ageAnswer = in.readLine();
                if (ageAnswer.equals("no") || ageAnswer.equals("yes")) {
                    if (ageAnswer.equals("yes")) {
                        out.println("Welcome to the kids area," + name + "! Let's play!\n To end  enter any character! ");
                        out.println("end");
                        break;
                    } else {
                        out.println("Welcome to the adult zone" + name + "! Let's drink!\n To end  enter any character!");
                        out.println("end");
                    }
                    break;
                } else {
                    out.println("Are you child? Enter yes or no");
                }
            }
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        System.out.println("Sever stopped!");
    }
}

