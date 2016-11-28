package gameplay;

import tile.Tile;

import java.util.*;
import board.*;

/**
 * Created by camerongera on 11/6/16.
 */
public class HumanPlayer extends Player {

    public HumanPlayer() {
        super.init();
    }

    @Override
    public TigerOption chooseMove(Tile t, Board b) {

        ArrayList<MoveOption> temp = b.potentialMoves(t);
        ArrayList<TigerOption> tigerMoves = b.potentialTigers(temp, t);
        Iterator<MoveOption> iter = temp.iterator();
        Iterator<TigerOption> iterTiger = tigerMoves.iterator();
        MoveOption currentMove;
        TigerOption currentTigerMove;
        int index = 0;
        System.out.println("Here is your board.\n" + b);
        System.out.println("Select an option to play tile " + t);
        while(iterTiger.hasNext()){
            currentTigerMove = iterTiger.next();
            System.out.println("Option " + index + ": " + (currentTigerMove.location/1000-Board.CENTER) + " " + (currentTigerMove.location%1000-Board.CENTER) + " with rotation: " + currentTigerMove.rotation + " tiger "+ currentTigerMove.tigerLocation);
            index++;
        }
        Scanner option = new Scanner(System.in);
        System.out.println("You are: "+super.toString());
        System.out.println("Which option would you like to play?");
        int response = option.nextInt();
        TigerOption selectedMove = tigerMoves.get(response);

        return selectedMove;
    }

    @Override
    public int chooseTigerPlacement() {
        return 0;
    }

}
