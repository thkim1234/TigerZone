import java.util.*;

class SlotMap extends HashMap<Integer,Slot>{

    public int getAdjKey(int key, int direction) {
        int tempKey = key;

        switch (direction) {
            case 0:
                tempKey = tempKey+1;
                break;
            case 1:
                tempKey = tempKey+1000;
                break;
            case 2:
                tempKey = tempKey-1;
                break;
            case 3:
                tempKey = tempKey-1000;
                break;
            default:
                break;
        }

        return tempKey;
    }

}
