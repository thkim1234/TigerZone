import java.io.*;
import java.net.*;

public class Main {
    public static void main(String args[]) {
        /*
        Game game = new Game(new HumanPlayer(), new HumanPlayer());
        GameVisualization printGame = new GameVisualization(game);

        System.out.println("Game being played");
        game.playGame();
        System.out.println("Game played, printing player ");
        printGame.printTileDeck();
        printGame.printBoard();
        printGame.printPlayerTurn();
        */

        String hostName = ""; //Given to us by Dave I'm assuming?
        int portNumber = 0; //Given to us by Dave I'm assuming?

        try (
                Socket socket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;

            while ((fromServer = in.readLine()) != null) {
                //What the server outputs
                System.out.println("Server: " + fromServer);
                //What we input
                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    System.out.println("Client: " + fromUser); //What we typed
                    out.println(fromUser);  //Send what we typed to server
                }
            }

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }

    };
}
