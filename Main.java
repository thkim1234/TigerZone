import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;

public class Main {

    //To read in user input
    static BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

    /*We need to instantiate two Game instances to keep track of the state of the board
    * which is held in the server, but we don't have access to it
    * */
    //AI ai = new AI(); (Maybe AI can be a subclass of Player so that it works in Game's constructor?)
    static HumanPlayer ai = new HumanPlayer();
    static HumanPlayer opponent = new HumanPlayer(); //(used to keep track of opponent's moves)
    static Game gameA = new Game(ai, opponent);
    static Game gameB = new Game(ai, opponent);
    static Map<String, Game> map = new HashMap<>();

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

        String hostName = "192.168.1.75"; //Given to us by Dave I'm assuming?
        int portNumber = 4444; //Given to us by Dave I'm assuming?
        String password = ""; //Given to us by Dave I'm assuming?

        try (
                Socket socket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            //Convert user input into a string
            String fromServer;
            //To read and display output from the server
            String fromUser;

            while ((fromServer = in.readLine()) != null) {
                //What the server outputs
                System.out.println("Server: " + fromServer);

                //What we input
                fromUser = processInput(fromServer);
                if (fromUser != null) {
                    //System.out.println("Client: " + fromUser); //What we typed
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

    }

    //Function to decide plan of action based on the message of the server
    public static String processInput(String serverMessage){
        //Tournament Authentication
        if(serverMessage.equals("THIS IS SPARTA!") || serverMessage.equals("HELLO!")){
            String message = "";
            try {
                message = stdIn.readLine();
            } catch(IOException e){
                System.err.println(e);
            }
            return message;
        }
        //AI's turn to make Move
        else if(serverMessage.contains("MAKE YOUR MOVE")){
            String gameId = getGameId(serverMessage);
            String tileString = getTileStringForAiMove(serverMessage);
            int moveNumber = getMoveNumber(serverMessage); //Haven't written this function yet
            return aiMakeMove(gameId, tileString);
            //To be replaced by return aiMakeMove(gameId, tileString, moveNumber) not sure yet tho
        }
        //Opponent makes move. Mimic opponent's move on our local game copy with respect to gameID
        else if(serverMessage.substring(0, 4) == "GAME"){
            //Opponent Move logic
            return "";
        }
        //Assign TileDeck for the copies of both local games
        else if(serverMessage.contains("THE REMAINING")){
            System.out.println("Inside THE REMAINING");
            String tileDeckString = getTileDeckString(serverMessage);
            return "";
        }
        else{
            return "";
        }
    }

    //Parse the server input into a tile deck and set it for both games
    public void setTileDeck(){}

    //Parse input to get GameID
    public static String getGameId(String serverMessage){
        System.out.println("Ai Move: ");
        String gameId = serverMessage.substring(23, 24);
        System.out.println("Game ID: " + gameId);
        if(map.size() == 0){
            map.put(gameId, gameA);
        }
        if(map.size() == 1) {
            map.put(gameId, gameB);
        }
        return gameId;
    }

    //This function is called when it's time for the AI to make a move
    public static String aiMakeMove(String gameId, String tileString){
        //This is the game the AI needs to make a move in
        Game game = map.get(gameId);
        /*
        AI DOES stuff here to make moves
        */

        /*return AI's move as a string. The following return statement shoudld be replaced with
        * a properly formatted AI move. Example - GAME A MOVE 1 PLACE TLTTP AT 0 1 90 TIGER 8*/


        return "AI place tile " + tileString + " in Game " + gameId;
    }

    //This function is called when it's time for the opponent to make a move in a game
    public void opponentMakeMove(Game game, String gameId){}

    public static String getTileStringForAiMove(String message){
        int startIndex = message.length() - 5;
        String tile = message.substring(startIndex);
        System.out.print("Tile: " + tile);
        return "";
    }

    public static String getTileDeckString(String message){
        Matcher m = Pattern.compile("\\[(.*?)\\]").matcher(message);
        String tileString = "";
        while(m.find()) {
            tileString = m.group(1);
        }
        System.out.println(tileString);
        return "";
    }

    public static int getMoveNumber(String message){
        return 0;
    }

}
