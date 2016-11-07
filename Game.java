import java.util.*;

public class Game{

  private Board board;
  private TileDeck tiles;
  private ArrayList<Player> players;
  private HumanPlayer player1 = new HumanPlayer();
  private HumanPlayer player2 = new HumanPlayer();
  private int currentPlayer;

  //init everything
  public Game(){
    board = new Board(this);
    tiles = new TileDeck();
    players = new ArrayList<Player>();
    players.add(0,player1);
    players.add(1,player2);
    board.placeTile(tiles.getTopTile(),new MoveOption(72072,0));
    currentPlayer = 0;
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

    // Should probably use some sort of iterator here so we have some flexibility.
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
