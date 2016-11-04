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
    Player player;
    MoveOption move;
    int meeplePlacement;

    while(!tiles.isEmpty()){

      tile = tiles.getTopTile();

      player = players.get(currentPlayer);

      move = player.chooseMove(tile, board);

      meeplePlacement = player.chooseMeeplePlacement(move.slot);

      board.placeTile(tile, move);

      board.placeMeepleOnBoard(move.slot, meeplePlacement);

      updatePlayer();
    }

  }
}
