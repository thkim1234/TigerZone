public class Main {
    public static void main(String args[]) {
        Game game = new Game(new HumanPlayer(), new HumanPlayer());
        GameVisualization printGame = new GameVisualization(game);

        System.out.println("Game being played");
        game.playGame();
        System.out.println("Game played, printing player ");
        printGame.printTileDeck();
        printGame.printBoard();
        printGame.printPlayerTurn();

    };
}
