import java.util.*;

public class Monastery extends Region{

    int openPortCount;
    protected HashMap<Field,Boolean> adjacentFields;

    public Monastery(){
        super.init();
        adjacentFields = new HashMap<Field,Boolean>();
        openPortCount = 8;
    }

    //for this, this isn't so much a port but tiles being placed
    public void closePort(int numberClosed){
        openPortCount -= numberClosed;
        if(openPortCount <= 0){
            notifyComplete();
        }
    }

    //add an adjacent field (for later notification)
    public void addAdjacent(Region adjacentRegion) {
        adjacentFields.put((Field) adjacentRegion, true);
    }

    private void notifyComplete(){
        Set<Field> adjFields = adjacentFields.keySet();
        for(Field field: adjFields){
            field.addCompleteMonastery(this);
        }
        super.score();
    }

    public int totalScore(){
        return 1+(1-openPortCount);
    }

    public String toString(){
        return Integer.toString(openPortCount) + " ports left to complete";
    }

}
