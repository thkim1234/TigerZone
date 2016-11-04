import java.util.*;

class SlotMap{

    private Map<Integer,Slot> slotMap;

    public SlotMap () {
        slotMap = new HashMap<Integer,Slot>();
    }

    public void put(int key, Slot slot) {
        slotMap.put(key,slot);
    }

    public Slot get(int key) {
        return slotMap.get(key);
    }

    public Slot getAdj(int key, int direction) {
        int tempKey = key;

        switch (direction) {
            case 0:
                tempKey = tempKey+100;
                break;
            case 1:
                tempKey = tempKey+1;
                break;
            case 2:
                tempKey = tempKey-100;
                break;
            case 3:
                tempKey = tempKey-1;
                break;
            default:
                break;
        }

        return slotMap.get(tempKey);
    }

}