package region;

import java.util.*;

public class Den extends Region{

    int openPortCount;

    public Den(){
        super.init();
        openPortCount = 8;
    }

    //for this, this isn't so much a port but tiles being placed
    public void closePort(int numberClosed){
        openPortCount -= numberClosed;
    }

    public boolean complete(){
        return openPortCount == 0;
    }

    public int totalScore(){
        return 9-openPortCount;
    }

    public String toString(){
        return "D: "+Integer.toString(openPortCount) + " ports left to complete\n";
    }

}