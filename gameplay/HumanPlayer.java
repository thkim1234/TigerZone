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
    public MoveOption chooseMove(Tile t, Board b) {

        ArrayList<MoveOption> temp = b.potentialMoves(t);
        ArrayList<TigerOption> tigerMoves = b.potentialTigers(temp, t);
        Iterator<MoveOption> iter = temp.iterator();
        MoveOption currentMove;
        int index = 0;
        System.out.println("Here is your board.\n" + b);
        System.out.println("Select an option to play tile " + t);
        while(iter.hasNext()){
            currentMove = iter.next();
            System.out.println("Option " + index + ": " + (currentMove.location/1000-Board.CENTER) + " " + (currentMove.location%1000-Board.CENTER) + " with rotation: " + currentMove.rotation);
            index++;
        }
        Scanner option = new Scanner(System.in);
        System.out.println("Which option would you like to play?");
        int response = option.nextInt();
        MoveOption selectedMove = temp.get(response);

        return selectedMove;
    }

    @Override
    public int chooseTigerPlacement() {
        return 0;
    }

}
