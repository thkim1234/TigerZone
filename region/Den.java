package region;

import java.util.*;

public class Den extends Region{

    int openPortCount;
    protected HashMap<Jungle,Boolean> adjacentJungles;

    public Den(){
        super.init();
        adjacentJungles = new HashMap<Jungle,Boolean>();
        openPortCount = 8;
    }

    //for this, this isn't so much a port but tiles being placed
    public void closePort(int numberClosed){
        openPortCount -= numberClosed;
        if(openPortCount <= 0){
            notifyComplete();
        }
    }

    //add an adjacent jungle (for later notification)
    public void addAdjacent(Region adjacentRegion) {
        adjacentJungles.put((Jungle) adjacentRegion, true);
    }

    private void notifyComplete(){
        Set<Jungle> adjJungles = adjacentJungles.keySet();
        for(Jungle jungle : adjJungles){
            jungle.addCompleteDen(this);
        }
        //super.score();
    }

    public boolean complete(){
        return openPortCount == 0;
    }

    public int totalScore(){
        return 9-openPortCount;
    }

    public String toString(){
        return Integer.toString(openPortCount) + " ports left to complete";
    }

}
