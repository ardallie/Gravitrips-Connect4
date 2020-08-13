public class Game {

  // Game board
  private Board board;
  // Player roster using Queue ADT
  private Roster roster;
  // number of pieces required to win (3, 4, 5 or 6)
  private int players;
  // number of pieces required to win (3, 4, 5 or 6)
  private int connectN;
  // width of the board
  private int boardWidth;
  // height of the board
  private int boardHeight;

  /**
   * Constructor.
   *
   * @param connectN    number of pieces required to win (3, 4, 5 or 6)
   * @param players     number of players (2 or 3)
   * @param boardWidth  number of columns on the board
   * @param boardHeight number of rows on the board
   */
  public Game(int connectN, int players, int boardWidth, int boardHeight) {
    this.board = new Board(boardWidth, boardHeight);
    this.roster = new Roster();
    this.players = players;
    this.connectN = connectN;
    this.boardWidth = boardWidth;
    this.boardHeight = boardHeight;
    TitleScreen.printTitleScreen(connectN);
  }

  /**
   * MAIN GAME LOOP
   * Main loop is very short since the players are rotated in the queue
   * and there is no need to differentiate between player types (Human, Bot)
   * using IF statements or manually repeat actions for each player.
   *
   * @return a winning player number, null if a game ends in a tie.
   */
  public Integer startGame() {
    prepareRoster();
    do {
      // refering to superclass Player rather than Bot or Human (generalization)
      Player player = roster.nextPlayer();
      boolean isWinner = playTurn(player);
      if (isWinner) {
        return player.getPlayerNum();
      }
    } while (!Gmaster.isBoardFull(board));
    // Game ended in a tie
    gameEndedTie(board);
    return null;
  }

  /**
   * Place the piece on the board in the given column.
   *
   * @param col       board column index
   * @param playerNum player number
   */
  private void makeMove(int col, int playerNum) {
    int row = 0;
    while (row < board.getHeight() - 1 && board.getField(row + 1, col) == 0) {
      row++;
    }
    board.setField(row, col, playerNum);
  }

  /**
   * Make a move by any type of player
   * This method uses generalisation by refering to the Player super type
   * rather than a Bot or Human. It allows executing the makeMove() method
   * without making a distinction what type of player is making that move.
   *
   * @param player generalised Player object (can be Bot or Human)
   * @return true if current player made a winning move, false if not
   */
  private boolean playTurn(Player player) {
    Integer move = player.makeMove(board);
    Integer playerNum = player.getPlayerNum();
    makeMove(move, playerNum);
    return gameWon(board, playerNum, connectN);
  }

  /**
   * Prepares the player roster.
   * Since the roster uses the queue ADT to manage the turns, any number
   * of players as well as any number of types (human, bot players) can be added to the game.
   */
  private void prepareRoster() {
    // instantiate all players
    Human human = new Human(1);
    Bot bot02 = new Bot(2, connectN, boardWidth, boardHeight);
    Bot bot03 = new Bot(3, connectN, boardWidth, boardHeight);
    // add player 1 to the roster
    roster.enqueue(human);
    // add player 2 to the roster
    roster.enqueue(bot02);
    // add player 3 to the roster (if required)
    if (players == 3) {
      roster.enqueue(bot03);
    }
  }

  /**
   * Check if a player has won the game, display a winning message if true.
   *
   * @param board     Board object
   * @param playerNum player number
   * @param connectN  number of pieces required to win
   * @return true if a player has won, false if not
   */
  private boolean gameWon(Board board, int playerNum, int connectN) {
    if (Gmaster.isWinner(board, playerNum, connectN)) {
      String marquee = board.getPlayerSymbol(playerNum) + " ";
      System.out.println();
      System.out.print(marquee.repeat(14));
      System.out.println();
      System.out.print(marquee.repeat(2));
      System.out.printf("   Player %d won!    ", playerNum);
      System.out.print(marquee.repeat(2));
      System.out.println();
      System.out.print(marquee.repeat(14));
      System.out.print("\n\n");
      board.printBoard();
      return true;
    }
    return false;
  }

  /**
   * Display a message when game ended a tie.
   *
   * @param board Board object
   */
  private void gameEndedTie(Board board) {
    String marquee = "*";
    System.out.println();
    System.out.print(marquee.repeat(27));
    System.out.println();
    System.out.print(marquee.repeat(2));
    System.out.print("   Game ended a tie!   ");
    System.out.print(marquee.repeat(2));
    System.out.println();
    System.out.print(marquee.repeat(27));
    System.out.print("\n\n");
    board.printBoard();
  }

}
