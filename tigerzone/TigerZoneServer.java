package tigerzone;
import java.net.*;
import java.io.*;

public class TigerZoneServer {
	public static void main(String[] args) throws IOException, InterruptedException{
		   if (args.length != 1) {
            System.err.println("Usage: java TigerZoneServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);

        try ( 
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        ) {
			String inputLine, outputLine;
            
            // Initiate conversation with client
            outputLine = "THIS IS SPARTA!";
            out.println(outputLine);
			
			inputLine = in.readLine();
			outputLine = "HELLO!";
			out.println(outputLine);
			
			inputLine = in.readLine();
			outputLine = "WELCOME testUser PLEASE WAIT FOR THE NEXT CHALLENGE";
			out.println(outputLine);
			
			outputLine = "NEW CHALLENGE 1 YOU WILL PLAY 1 MATCH\n BEGIN ROUND 1 OF 1\n YOUR OPPONENT IS PLAYER Blue\n STARTING TILE IS TLTJ- AT 0 0 0";
			out.println(outputLine);
			
			outputLine = "THE REMAINING TILES ARE [TLTTP LJTJ- JLJL- JJTJX JLTTB TLLT-]";
			out.println(outputLine);
			
			outputLine = "MATCH BEGINS IN 15 SECONDS";
			out.println(outputLine);
			Thread.sleep(15000);

			outputLine = "MAKE YOUR MOVE IN GAME A WITHIN 1 SECOND: MOVE 1 PLACE TLTTP";
			out.println(outputLine);

			inputLine = in.readLine();
			outputLine = "GAME B MOVE 1 PLAYER Blue PLACED TLTTP AT 0 1 90 TIGER 8";
			out.println(outputLine);

			outputLine = "MAKE YOUR MOVE IN GAME B WITHIN 1 SECOND: MOVE 2 PLACE LJTJ-";
			out.println(outputLine);

			inputLine = in.readLine();
			outputLine = "GAME A MOVE 2 PLAYER Blue PLACED LJTJ- AT 0 2 180 TIGER 8";
			out.println(outputLine);

			outputLine = "MAKE YOUR MOVE IN GAME A WITHIN 1 SECOND: MOVE 3 PLACE JLJL-";
			out.println(outputLine);

			inputLine = in.readLine();
			outputLine = "GAME B MOVE 3 PLAYER Blue PLACED JLJL- AT 1 0 0 TIGER 4";
			out.println(outputLine);

			outputLine = "MAKE YOUR MOVE IN GAME B WITHIN 1 SECOND: MOVE 4 PLACE JJTJX";
			out.println(outputLine);

			inputLine = in.readLine();
			outputLine = "GAME A MOVE 4 PLAYER Blue PLACED JJTJX AT 1 1 270 TIGER 5";
			out.println(outputLine);

			outputLine = "MAKE YOUR MOVE IN GAME A WITHIN 1 SECOND: MOVE 5 PLACE JLTTB";
			out.println(outputLine);

			inputLine = in.readLine();
			outputLine = "GAME B MOVE 5 PLAYER Blue PLACED JLTTB AT 2 0 180 TIGER 1";
			out.println(outputLine);

			outputLine = "MAKE YOUR MOVE IN GAME B WITHIN 1 SECOND: MOVE 6 PLACE TLLT-";
			out.println(outputLine);

			inputLine = in.readLine();
			outputLine = "GAME A MOVE 6 PLAYER Blue PLACED TLLT- AT 0 -1 270 CROCODILE";
			out.println(outputLine);

			outputLine = "GAME A OVER PLAYER Red 6 PLAYER Blue 14\n" + "GAME B OVER PLAYER Red 20 PLAYER Blue 19";
			out.println(outputLine);
		} catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
	}
}