class GameVisualization {
    Game game;

    public GameVisualization (Game game) {
        this.game = game;
    }

    public void printTileDeck() {
        System.out.println(game.getTiles());
    }

    public void printPlayerTurn() {
        System.out.println(game.getCurrentPlayer());
    }
 }