//import gameplay.GameVisualization;
//import gameplay.HumanPlayer;
//import gameplay.*;
//
//public class Main {
//    public static void main(String args[]) {
//        Game game = new Game(new HumanPlayer(), new HumanPlayer());
//        GameVisualization printGame = new GameVisualization(game);
//
//        System.out.println("gameplay.Game being played");
//        game.playGame();
//        System.out.println("gameplay.Game played, printing player ");
//        //printGame.printTileDeck();
//        //printGame.printBoard();
//        //printGame.printPlayerTurn();
//
//    };
//}


import board.Board;
import gameplay.*;
import tile.Tile;
import tile.TileDeck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    //To read in user input
    static BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

    /*We need to instantiate two Game instances to keep track of the state of the board
    * which is held in the server, but we don't have access to it
    * */
    //AI ai = new AI(); (Maybe AI can be a subclass of Player so that it works in Game's constructor?)

    static StupidAi ai = new StupidAi();
    static HumanPlayer opponent = new HumanPlayer(); //(used to keep track of opponent's moves)
    static Game gameA = new Game(ai, opponent);
    static Game gameB = new Game(ai, opponent);
    static Map<String, Game> map = new HashMap<>();
    static String hostName;
    static int portNumber;
    static String tourneyPassword;
    static String username;
    static String password;

    public static void main(String args[]) {

//        Scanner in = new Scanner(System.in);
//        String input = in.nextLine();
//        String output;
//        while(input != "FIN"){
//            System.out.println(processInput(input));
//            System.out.println("GAME A:\n"+gameA);
//            System.out.println("GAME B:\n"+gameB);
//            System.out.println("\n\n\n");
//            input = in.nextLine();
//
//        }

        if (args.length != 5) {
            System.err.println("java Main <hostname> <port#> <tourneypassword> <username> <password>");
            System.exit(1);
        }

        hostName = args[0]; //Given to us by Dave I'm assuming?
        portNumber =  Integer.parseInt(args[1]);; //Given to us by Dave I'm assuming?
        tourneyPassword = args[2]; //Given to us by Dave I'm assuming?
        username = args[3];
        password = args[4];

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
                if (fromUser != null && fromUser != "") {
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

    }

    //Function to decide plan of action based on the message of the server
    public static String processInput(String serverMessage){
        //Tournament Authentication
        if(serverMessage.equals("THIS IS SPARTA!")){
            String message = "";
            message = "JOIN " + tourneyPassword;
            return message;
        }
        else if(serverMessage.equals("HELLO!")){
            String message = "";
            message = "I AM " + username + " " + password;
            return message;
        }
        //AI's turn to make Move
        else if(serverMessage.contains("MAKE YOUR MOVE")){
            //String gameId = getGameId(serverMessage);
            String[] parsedString = serverMessage.split(" "); //Parse
            String moveStr = serverMessage.substring(serverMessage.indexOf(':')+1);
            String gameId = getGameId(serverMessage);
//            System.out.println("Game ID for AI: " + gameId);
            String tileString = getTileStringForAiMove(serverMessage);
            String aiMoveRecieved = aiMakeMove(gameId, tileString);
            if(aiMoveRecieved.contains(" TIGER -1")){
                String parsedAIResponse = aiMoveRecieved.substring(26);
                aiMoveRecieved = parsedAIResponse + "NONE";
            }
            if(aiMoveRecieved.contains("UNPLACEABLE")){
                moveStr = " " + parsedString[9] + " " + parsedString[10] + " TILE " + tileString + " ";
            }
            String returnStr = "GAME "+gameId+moveStr+aiMoveRecieved;
//            System.out.println("Client: " + returnStr);
            return returnStr;
            //To be replaced by return aiMakeMove(gameId, tileString, moveNumber) not sure yet tho
        }
        //Opponent makes move. Mimic opponent's move on our local game copy with respect to gameID
        else if(serverMessage.contains("MOVE") && serverMessage.contains("PLAYER") && !serverMessage.contains("PLAYER " + username)){
            //Opponent Move logic
            String gameId = getGameId(serverMessage);
//            System.out.println("GAME ID: " + gameId);
            Game gamePlayed = map.get(gameId);
            opponentMakeMove(gamePlayed, gameId, serverMessage);
            return "";
        }
        //Assign TileDeck for the copies of both local games
        else if(serverMessage.contains("THE REMAINING")){
            String tileDeckString = getTileDeckString(serverMessage);
//            System.out.println("TILE DECK: " + tileDeckString);
            TileDeck deck = new TileDeck(tileDeckString);
            gameA.setTileDeck(deck);
            gameB.setTileDeck(deck);
            return "";
        }
        else if(serverMessage.contains("END OF ROUND")){
            resetMatch();
            return "";
        }

        else{

            return "";
        }
    }

    //Parse the server input into a tile deck and set it for both games
    public void setTileDeck(){

    }

    //Parse input to get GameID
    public static String getGameId(String serverMessage){
        String gameId;
        if(serverMessage.contains("MOVE") && serverMessage.contains("PLAYER ")){
            gameId = serverMessage.substring(5, (serverMessage.indexOf("MOVE")-1));
        }
        else {
            gameId =  serverMessage.substring(23, (serverMessage.indexOf("WITHIN") - 1));
        }
//        System.out.println("Game ID: " + gameId);
        if(map.size() == 0){
            map.put(gameId, gameA);
        }
        if(map.size() == 1 && !map.containsKey(gameId)) {
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


        return game.makeMove(new Tile(tileString), ai);
    }

    //This function is called when it's time for the opponent to make a move in a game
    public static void opponentMakeMove(Game game, String gameId, String serverMessage){
        String[] parsedString = serverMessage.split(" "); //Parse
        int placed = serverMessage.indexOf("PLACED");
        int unplaceable = serverMessage.indexOf("UNPLACEABLE");
        if(placed != -1){
            Tile tile = new Tile(parsedString[7]);
            int x = Integer.parseInt(parsedString[9]);
            int y = Integer.parseInt(parsedString[10]);
            int location = (x+Board.CENTER)*1000+y+Board.CENTER;
            int orientation = Integer.parseInt(parsedString[11])/90;
            String creature = parsedString[12];
            int zone;
            char tiger;
            if(parsedString[12].equals("TIGER")){
                zone = Integer.parseInt(parsedString[13]);
                tiger = 'Y';
            } else {
                zone = -1;
                tiger = 'N';
            }
//             System.out.println("location: " + location + " orientation: " + orientation + " zone: " + zone + " tiger: " + tiger + " tile: " + tile);
            game.setMove(tile, opponent, new TigerOption(location,orientation,zone,tiger));

        } else if (unplaceable != -1){
            if(serverMessage.indexOf("PASS") != -1){
                return;
            } else if (serverMessage.indexOf("RETRIEVE TIGER") != -1){
                int x = Integer.parseInt(parsedString[12]);
                int y = Integer.parseInt(parsedString[13]);
                int location = (x+Board.CENTER)*1000+y+Board.CENTER;
                game.getBoard().placeTigerOnBoard(location, 1, opponent);
            } else if (serverMessage.indexOf("ADD ANOTHER TIGER") != -1){
                int x = Integer.parseInt(parsedString[13]);
                int y = Integer.parseInt(parsedString[14]);
                int location = (x+Board.CENTER)*1000+y+Board.CENTER;
                game.getBoard().removeTigerFromBoard(location);
            }
        }
    }

    public static String getTileStringForAiMove(String message){
        int startIndex = message.length() - 5;
        String tile = message.substring(startIndex);
//        System.out.print("Tile: " + tile);
        return tile;
    }

    public static String getTileDeckString(String message){
        Matcher m = Pattern.compile("\\[(.*?)\\]").matcher(message);
        String tileString = "";
        while(m.find()) {
            tileString = m.group(1);
        }
//        System.out.println(tileString);
        return tileString;
    }

    public static void resetMatch(){
        ai = new StupidAi(); // dumbie AI. Change if possible
        opponent = new HumanPlayer(); //(used to keep track of opponent's moves)
        gameA = new Game(ai, opponent);
        gameB = new Game(ai, opponent);
        map = new HashMap<>();
    }

}
