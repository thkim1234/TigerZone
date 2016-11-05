import java.util.*;

public class Game{

  private Board board;
  private TileDeck tiles;
  private ArrayList<Player> players;
  private int currentPlayer;

  //init everything
  public Game(){
    board = new Board();
    tiles = new TileDeck();
    players = new ArrayList<Player>();
    currentPlayer = 0;
  }

  public void displayBoard(){

  }

  //lol we should use a player iterator
  private void updatePlayer(){
    if(currentPlayer == players.size()){
      currentPlayer = 0;
    }
    else{
      currentPlayer++;
    }
  }

  public void playGame(){

    ArrayList<MoveOption> options;
    Tile tile;
    MoveOption move;
    int meeplePlacement;

    while(!tiles.isEmpty()){

      tile = tiles.getTopTile();

      move = players.get(currentPlayer).chooseMove(tile, board);

      meeplePlacement = players.get(currentPlayer).chooseMeeplePlacement();

      board.placeTile(tile, move);

      board.placeMeepleOnBoard(move.location, meeplePlacement);

      updatePlayer();
    }

  }

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
