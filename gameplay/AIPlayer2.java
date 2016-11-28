package gameplay;

import board.Board;
import tile.Tile;
import tile.TileDeck;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Joseph OConnor on 11/26/16.
 */
/* This is the controller AI.
* This AI handles the temp.size() number of games going for 10 moves
* and choosing the best move based off of those games
*/
public class AIPlayer2 extends Player {

    public AIPlayer2() {
        super.init();
    }

    @Override
    public TigerOption chooseMove(Tile t, Board b, TileDeck tiles){
        ArrayList<MoveOption> temp = b.potentialMoves(t);
        ArrayList<TigerOption> tigerMoves = b.potentialTigers(temp, t);
        Iterator<MoveOption> iter = temp.iterator();
        Iterator<TigerOption> iterTiger = tigerMoves.iterator();
        MoveOption currentMove;
        TigerOption currentTigerMove;
        if(temp.size() == 0){
            return passMethod();
        }
        else{
            int[] gameScores;
            gameScores = new int[temp.size()];
            for(int i = 0; i < temp.size(); i++){
                AIPlayer1 p1 = new AIPlayer1();
                AIPlayer1 p2 = new AIPlayer1();
                Game game2 = new Game(p1, p2, tiles, b);
                game2.setMove(t, p1, tigerMoves.get(i)); //forces first move of new game to be possible move
                for(int j = 0; j < 10; j++){
                    if(!game2.getTiles().isEmpty()){
                        Tile tile1 = game2.getTiles().getTopTile();
                        TigerOption move = p2.chooseMove(tile1, game2.getBoard());
                        game2.setMove(tile1, p2, move);
                    }
                    if(!game2.getTiles().isEmpty()){
                        Tile tile1 = game2.getTiles().getTopTile();
                        TigerOption move = p1.chooseMove(tile1, game2.getBoard());
                        game2.setMove(tile1, p1, move);
                    }
                }
                int score = p1.getScore() - p2.getScore();
                gameScores[i] = score;
            }
            int x = 0;
            for(int i = 0; i < gameScores.length; i++){
                if(gameScores[x] < gameScores[i]){
                    x = i;
                }
            }
            TigerOption selectedMove = tigerMoves.get(x);
            return selectedMove;
        }
    }

    @Override
    public int chooseTigerPlacement(){
        return 0;
    }
}