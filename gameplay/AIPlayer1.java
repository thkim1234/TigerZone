package gameplay;

import board.Board;
import tile.Tile;
import tile.TileDeck;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Joseph OConnor on 11/26/16.
 */
public class AIPlayer1 extends Player {

    public AIPlayer1() {
        super.init();
    }

    @Override
    public TigerOption chooseMove(Tile t, Board b, TileDeck tiles){
        ArrayList<MoveOption> temp = b.potentialMoves(t);
        ArrayList<TigerOption> tigerMoves = b.potentialTigers(temp, t);
        Iterator<MoveOption> iter = temp.iterator();
//        Iterator<TigerOption> iterTiger = tigerMoves.iterator();
        MoveOption currentMove;
        TigerOption currentTigerMove;
        if(temp.size() == 0)
            return passMethod(); //if tile can't be placed anywhere
        else{
            int[] moveValue;
            moveValue = new int[tigerMoves.size()];
            Iterator<TigerOption> iterTiger = tigerMoves.iterator();
            int index = 0;
//            TigerOption currentMove;
            while(iterTiger.hasNext()){
                currentTigerMove = iterTiger.next();
                moveValue[index] = calcMoveValue(currentTigerMove);//A step 2
                index++;
            }

            TigerOption currentMove1;
            int x = 0;
            currentMove1 = tigerMoves.get(x);
            for(int i = 0; i < moveValue.length; i++){
                if(moveValue[i] > moveValue[x])
                    currentMove1 = tigerMoves.get(i);
            }
            return currentMove1;
        }
    }

    @Override
    public int chooseTigerPlacement() {
        return 0;
    }

    public int calcMoveValue(TigerOption move){
        int score = 0;
        if(move.tigerType == 'L'){  //lake
            score += 5;
        }
        else if(move.tigerType == 'X'){  //Den
            score += 4;
        }
        else if(move.tigerType == 'T'){  //Trail
            score += 3;
        }
        else if(move.tigerType == 'J'){  //Jungle
            score += 2;
        }
        else if(move.tigerType == 'N'){  //None
            score += 1;
        }
        return score;
    }

}