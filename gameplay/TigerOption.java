package gameplay;

//to group all the information relevant to a move (the slot and the tile's rotation)
public class TigerOption {

    public int location;
    public int rotation;
    public int tigerLocation = -1;
    public char tigerType = 'N';
    public int score;

    public TigerOption(int location, int rotation, int tigerLocation, char tigerType, int score) {
        this.location = location;
        this.rotation = rotation;
        this.tigerLocation = tigerLocation;
        this.tigerType = tigerType;
        this.score = score;
    }
}
