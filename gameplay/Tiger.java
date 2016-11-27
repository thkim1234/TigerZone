package gameplay;

import region.*;

/**
 * Created by lexieforos on 11/26/16.
 */
public class Tiger {
    public Player owner;
    private RegionContainer region;
    public Tiger(Player owner){
        this.owner = owner;
    }
    public Region getRegion() { return region.getRegion(); }
    public void setRegion(RegionContainer region){ this.region = region; }
    public String toString(){ return "tiger"; }
}
