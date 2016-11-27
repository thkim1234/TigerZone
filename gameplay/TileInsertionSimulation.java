package gameplay;

import region.Region;
import tile.Tile;
import tile.TileDeck;
import board.*;

import java.util.*;

/* Runs the game
tracks:
- players: the players
- board: the current board
*/


public class TileInsertionSimulation extends Game{

    private Board board;
    private ArrayList<Player> players;
    private TileDeck tiles;
    private HumanPlayer player1 = new HumanPlayer();
    private HumanPlayer player2 = new HumanPlayer();
    private int currentPlayer;
    private int NUM_PLAYERS;

    //initializes a game with the players given
    public TileInsertionSimulation(Player... players){
        board = new Board();
        tiles = new TileDeck();

        //add all of the players passed into the constructor
        this.players = new ArrayList<Player>();
        for(Player player: players){
            this.players.add(player);
        }

        NUM_PLAYERS = this.players.size();

        //place the first tile at the origin
        //board.placeTile(tiles.getTopTile(),new gameplay.MoveOption(Board.CENTER*1001,0));

        //set the current player to the first of the given players
        currentPlayer = 0;
    }

    //self explanatory
    private void updatePlayer(){
        currentPlayer = (currentPlayer+1)%NUM_PLAYERS;
    }

    //runs the game
    public void playGame(){

        Tile tile;
        MoveOption move;
        String garbage;

        Scanner in = new Scanner(System.in);

        board.placeTile(new Tile("TLTJ-"), new MoveOption(72072,0));

        // while we have tiles to place
        while(true){
            System.out.println(board);
            System.out.println(players);
            System.out.println("please type move");

            String command = in.next();

            if(command.charAt(0) == 'd'){
                finish();
                return;
            }

            tile = new Tile(command);
            move = new MoveOption(1000*(in.nextInt()+72)+in.nextInt()+72, in.nextInt()/90);
            board.placeTile(tile, move);

            garbage = in.next();
            if(!garbage.equals("NONE")){
                board.placeTigerOnBoard(move.location, in.nextInt(), players.get(currentPlayer));
            }

            board.updateScores();

            //move forward in terms of turns
            updatePlayer();
        }

    }

    //accessors:

    private void finish(){
        //board.doAllScores();
        HashMap<Region, Boolean> regionsToScore = new HashMap<Region, Boolean>();
        for(Player player: players){
            regionsToScore.putAll(player.relevantRegions());
        }
        for(Region region: regionsToScore.keySet()){
            region.score();
        }
        System.out.println(players);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getCurrentPlayer() {
        return currentPlayer%2;
    }

    public TileDeck getTiles() {
        return tiles;
    }
}
