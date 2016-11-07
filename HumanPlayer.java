import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by camerongera on 11/6/16.
 */
public class HumanPlayer extends Player {

    public HumanPlayer() {

    }

    @Override
    public MoveOption chooseMove(Tile t, Board b) {

        ArrayList<MoveOption> temp = b.potentialMoves(t);
        Iterator<MoveOption> iter = temp.iterator();
        int index = 0;
        System.out.println("Here's your board: ");
        System.out.println(b);
        System.out.println("Select an option to play this tile: " + t);
        while(iter.hasNext()){
            MoveOption m = iter.next();
            int i = m.location%1000-72;
            int j = m.location/1000-72;
            System.out.println("Option " + index + ": " + i + " "+ j + " with rotation: " + m.rotation);
            index++;
        }
        Scanner option = new Scanner(System.in);
        System.out.println("Which option would you like to play?");
        int response = option.nextInt();
        MoveOption selectedMove = temp.get(response);

        return selectedMove;
    }

    @Override
    public int chooseMeeplePlacement() {
        return 0;
    }

}
