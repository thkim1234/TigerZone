package gameplay;

import tile.Tile;
import tile.TileDeck;
import board.*;

import java.util.*;


/* Runs the game
tracks:
- players: the players
- board: the current board
*/

public class Game{

  private Board board = new Board();
  private TileDeck tiles = new TileDeck();
  private ArrayList<Player> players;
  private HumanPlayer player1 = new HumanPlayer();
  private HumanPlayer player2 = new HumanPlayer();
  private int currentPlayer;
  private int NUM_PLAYERS;

  //initializes a game with the players given
  public Game(Player ... players){

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
    int tigerPlacement;

    // while we have tiles to place
    while(!tiles.isEmpty()){

      //get the tile to be placed
      tile = tiles.getTopTile();

      //ask the current player for his choice of move
      move = players.get(currentPlayer).chooseMove(tile, board);

      //ask the current player for his choice of tiger placement
      tigerPlacement = players.get(currentPlayer).chooseTigerPlacement();

      //place the tile as the player specified
      board.placeTile(tile, move);

      //place the tiger as the player specified
      board.placeTigerOnBoard(move.location, tigerPlacement, players.get(currentPlayer));

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
