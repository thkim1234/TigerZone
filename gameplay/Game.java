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
  private Player player1 = new HumanPlayer();
  private Player player2 = new HumanPlayer();
  private int currentPlayer;
  private int NUM_PLAYERS;

  //initializes a game with the players given
  public Game(Player ... players){
    board = new Board();

    //add all of the players passed into the constructor
    this.players = new ArrayList<Player>();
    for(Player player: players){
      this.players.add(player);
    }

    NUM_PLAYERS = this.players.size();

    board.placeTile(new Tile("TLTJ-"),new MoveOption(Board.CENTER*1001,0));

    //set the current player to the first of the given players
    currentPlayer = 0;
  }
  
  public Game(Player player1, Player player2, TileDeck tiles, Board b){
	this.tiles = tiles;
	this.player1 = player1;
	this.player2 = player2;
	this.board = b;
	this.players = new ArrayList<Player>();
	this.players.add(player1);
	this.players.add(player2);
	NUM_PLAYERS = this.players.size();
	currentPlayer = 0;
}

  //self explanatory
  private void updatePlayer(){
    currentPlayer = (currentPlayer+1)%NUM_PLAYERS;
  }

  //runs the game
//  public void playGame(){
//
//    Tile tile;
//    MoveOption move;
//    int tigerPlacement;
//    Scanner in = new Scanner(System.in);
//
//    // while we have tiles to place
//    while(!tiles.isEmpty()){
//
//      //get the tile to be placed
//      tile = new Tile(in.next());//tiles.getTopTile();
//
//      makeMove(tile, players.get(currentPlayer), players.get(currentPlayer).chooseMove(tile, board));
//
////      //ask the current player for his choice of move
////      move = players.get(currentPlayer).chooseMove(tile, board);
////
////      //ask the current player for his choice of tiger placement
////      tigerPlacement = players.get(currentPlayer).chooseTigerPlacement();
////
////      //place the tile as the player specified
////      board.placeTile(tile, move);
////
////      //place the tiger as the player specified
////      board.placeTigerOnBoard(move.location, tigerPlacement, players.get(currentPlayer));
//
//      //move forward in terms of turns
//
//      board.updateScores();
//
//      updatePlayer();
//    }
//
//  }

  public String makeMove(Tile tile, Player player){

    //choose it
    TigerOption move = player.chooseMove(tile, board, tiles);

    String moveStr;

    //set it
    if(move != null){
      moveStr = Integer.toString(move.location/1000-Board.CENTER)
              +" "+Integer.toString(move.location%1000-Board.CENTER)
              +" "+Integer.toString(move.rotation*90)
              +" TIGER "+Integer.toString(move.tigerLocation);
      setMove(tile, player, move);
    } else {
      moveStr = "UNPLACEABLE PASS";
    }

    return moveStr;
  }

  public void setMove(Tile tile, Player player, TigerOption move){
    board.placeTile(tile,new MoveOption(move.location, move.rotation));
    if(move.tigerLocation != -1) {
      board.placeTigerOnBoard(move.location, move.tigerLocation, player);
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
        return currentPlayer%NUM_PLAYERS;
    }

    public TileDeck getTiles() {
        return tiles;
    }
    //Used to set the deck of tiles equal to what the server passes to us
    public void setTileDeck(TileDeck deck) { this.tiles = deck; }

    public String toString(){
      return "BOARD: " + board + "\nPLAYERS" + players + "\n";
    }
}
