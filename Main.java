import gameplay.GameVisualization;
import gameplay.HumanPlayer;
import gameplay.TileInsertionSimulation;

public class Main {
    public static void main(String args[]) {
        TileInsertionSimulation game = new TileInsertionSimulation(new HumanPlayer(), new HumanPlayer());
        GameVisualization printGame = new GameVisualization(game);

        System.out.println("gameplay.Game being played");
        game.playGame();
        System.out.println("gameplay.Game played, printing player ");
        printGame.printTileDeck();
        printGame.printBoard();
        printGame.printPlayerTurn();

    };
}
