package gameplay;

import board.Board;
import tile.Tile;
import tile.TileDeck;

import java.util.ArrayList;

/**
 * Created by camerongera on 11/6/16.
 */
public class StupidAi extends Player {

    public StupidAi() {
        super.init();
    }

    @Override
    public TigerOption chooseMove(Tile t, Board b, TileDeck tiles) {

        ArrayList<MoveOption> temp = b.potentialMoves(t);
        ArrayList<TigerOption> tigerMoves = b.potentialTigers(temp, t);
//        Iterator<MoveOption> iter = temp.iterator();
//        Iterator<TigerOption> iterTiger = tigerMoves.iterator();
//        MoveOption currentMove;
//        TigerOption currentTigerMove;
//        int index = 0;
//        System.out.println("Here is your board.\n" + b);
//        System.out.println("Select an option to play tile " + t);
//        while(iterTiger.hasNext()){
//            currentTigerMove = iterTiger.next();
//            System.out.println("Option " + index + ": " + (currentTigerMove.location/1000-Board.CENTER) + " " + (currentTigerMove.location%1000-Board.CENTER) + " with rotation: " + currentTigerMove.rotation + " tiger "+ currentTigerMove.tigerLocation);
//            index++;
//        }
//        Scanner option = new Scanner(System.in);
//        System.out.println("You are: "+super.toString());
//        System.out.println("Which option would you like to play?");
//        int response = option.nextInt();
        if(tigerMoves.size()>0)
            if(this.availableTigers.size() == 0){
                boolean x = true;
                int i = 0;
                while(x){
                    if(tigerMoves.get(i).tigerType == 'N'){
                        x = false;
                        return tigerMoves.get(i);
                    }
                    i++;
                }
            }
            else{
                for(int i = 0; i<tigerMoves.size(); i++){
                    if(tigerMoves.get(i).tigerType == 'L')
                        return tigerMoves.get(i);
                }
                for(int i = 0; i<tigerMoves.size(); i++){
                    if(tigerMoves.get(i).tigerType == 'X')
                        return tigerMoves.get(i);
                }
                for(int i = 0; i<tigerMoves.size(); i++){
                    if(tigerMoves.get(i).tigerType == 'T')
                        return tigerMoves.get(i);
                }
                for(int i = 0; i<tigerMoves.size(); i++){
                    if(tigerMoves.get(i).tigerType == 'J')
                        return tigerMoves.get(i);
                }
                for(int i = 0; i<tigerMoves.size(); i++){
                    if(tigerMoves.get(i).tigerType == 'N')
                        return tigerMoves.get(i);
                }
            }
        else
            return null;
    }

    @Override
    public int chooseTigerPlacement() {
        return 0;
    }

}

