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
    public TileInsertionSimulation(Player ... players){
        board = new Board();
        tiles = new TileDeck();

        //add all of the players passed into the constructor
        this.players = new ArrayList<Player>();
        for(Player player: players){
            this.players.add(player);
        }

        NUM_PLAYERS = this.players.size();

        //place the first tile at the origin
        //board.placeTile(tiles.getTopTile(),new MoveOption(Board.CENTER*1001,0));

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
        int meeplePlacement;

        Scanner in = new Scanner(System.in);

        // while we have tiles to place
        while(true){

            System.out.println("please type the tile code");

            //get the tile to be placed
            tile = new Tile(in.next());

            //ask the current player for his choice of move
            move = players.get(currentPlayer).chooseMove(tile, board);

            //ask the current player for his choice of meeple placement
            meeplePlacement = players.get(currentPlayer).chooseMeeplePlacement();

            //place the tile as the player specified
            board.placeTile(tile, move);

            //place the meeple as the player specified
            board.placeMeepleOnBoard(move.location, meeplePlacement);

            //move forward in terms of turns
            updatePlayer();
        }

    }

    //accessors:

    public Board getBoard() {
        return board;
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
