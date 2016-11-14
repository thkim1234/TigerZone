public abstract class Region{

  private HashMap<Player,Integer> meeplesByPlayer;

  public abstract void absorb(Region otherRegion);

  public void addMeeple(Player owner){
    if(!meeplesByPlayer.containsKey(owner)){
      meeplesByPlayer.put(owner,1);
    } else {
      meeplesByPlayer.get(owner)++;
    }
  }

  public abstract int totalScore();


  public int scoreByPlayer(){

  }
}
