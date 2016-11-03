import java.util.*;

public class Game{

  private Board board;
  private TileDeck tiles;
  private ArrayList<Player> players;
  private int currentPlayer;

  public Game(){
    //resets everything
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

    while(!tiles.isEmpty()){

      Tile tile = tiles.getTopTile();

      Player player = players.get(currentPlayer);

      player.chooseMove(tile);

      board.placeTile(player.getChosenLocation(), player.getChosenOrientation(), tile);

      board.placeMeepleOnBoard(player.getChosenLocation(), player.getChosenMeeplePlacement());

      updatePlayer();
    }

  }
}
