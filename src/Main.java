public class Main {

  /**
   * Entry point.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    // 1st CLI argument:
    // number of pieces required to win, defaults to 4 if no valid argument.
    int connectN = args.length >= 1 ? Console.getCliConnectN(args[0]) : 4;
    // 2nd CLI argument:
    // number of players (2 or 3), defaults to 2 if no valid argument
    int players = args.length >= 3 ? Console.getCliPlayers(args[1]) : 2;
    // instantiate a new game
    Game game = new Game(connectN, players, 7, 6);
    // game returns the winning player number so it can be passed to (future) stats engine.
    Integer winner = game.startGame();
  }
}
