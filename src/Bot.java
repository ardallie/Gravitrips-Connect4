import java.util.ArrayList;

/**
 * CPU Player.
 */
public class Bot extends Player {

  // player number
  public Integer playerNum;
  // number of pieces required to win (3-6)
  private int connectN;
  // board fragment to be analysed for the best possible move
  private Integer[] window;

  private static final Integer PLAYER_PIECE = 1;
  private static final Integer CPU_PIECE = 2;

  /**
   * Constructor.
   *
   * @param playerNum   player number
   * @param connectN    number of pieces required to win (3-6)
   * @param boardWidth  number of columns on the board
   * @param boardHeight number of rows on the board
   */
  public Bot(int playerNum, int connectN, int boardWidth, int boardHeight) {
    this.window = new Integer[connectN];
    this.connectN = connectN;
    this.playerNum = playerNum;
    Board virtualBoard = new Board(boardWidth, boardHeight);
    virtualBoard.clearBoard();
  }

  /**
   * Get the current player number.
   *
   * @return player number
   */
  public Integer getPlayerNum() {
    return this.playerNum;
  }

  /**
   * Randomly selects the column.
   *
   * @param board Board object
   * @return column index
   */
  private Integer makeRandomMove(Board board) {
    ArrayList<Integer> columns = Gmaster.getAvailableColumns(board);
    int len = columns.size();
    if (len > 0) {
      return columns.get((int) (Math.random() * len));
    }
    return null;
  }

  /**
   * Selects the most optimal column to move using the Minimax algorithm.
   * Important! This functionality works best with two players and N = 4
   *
   * @param board Board object
   * @return column number that is the most optimal in the current board configuration.
   */
  public Integer makeMove(Board board) {
    Double ninf = Double.NEGATIVE_INFINITY;
    Double pinf = Double.POSITIVE_INFINITY;
    Pair<Integer, Double> minimax = miniMax(board, 5, ninf, pinf, true);
    return minimax.getFirst();
  }

  /**
   * Performs the recursive search for the most optimal move.
   *
   * @param board Board object
   * @param depth number of "moves ahead" to be represented as nodes.
   * @param a     alpha parameter
   * @param b     beta parameter
   * @param isMax is maximising own gain or minimising opponent's
   * @return Pair object containing the column number and the corresponding score.
   */
  private Pair<Integer, Double> miniMax(Board board, int depth, Double a, Double b, boolean isMax) {
    Integer column = makeRandomMove(board);
    boolean isTerminalNode = Gmaster.isTerminalNode(board, connectN);
    // base case
    if (depth == 0) {
      return new Pair<>(null, (double) scorePosition(board, CPU_PIECE));
    }
    if (isTerminalNode) {
      if (Gmaster.isWinner(board, CPU_PIECE, connectN)) {
        return new Pair<>(null, (double) 1000000000);
      } else if (Gmaster.isWinner(board, PLAYER_PIECE, connectN)) {
        return new Pair<>(null, (double) -1000000000);
      } else {
        return new Pair<>(null, (double) 0);
      }
    }
    // end of base case
    if (isMax) {
      double value = Double.NEGATIVE_INFINITY;
      for (int col = 0; col < board.getWidth(); col++) {
        Integer row = Gmaster.getAvailableRow(board, col);
        if (row != null) {
          Board virtualBoard = Utils.cloneBoard(board);
          virtualBoard.setField(row, col, CPU_PIECE);
          Double score = miniMax(virtualBoard, depth - 1, a, b, false).getSecond();
          if (score > value) {
            value = score;
            column = col;
          }
          a = Math.max(a, value);
          if (a >= b) {
            break;
          }
        }
      }
      return new Pair<>(column, value);
    } else {
      double value = Double.POSITIVE_INFINITY;
      for (int col = 0; col < board.getWidth(); col++) {
        Integer row = Gmaster.getAvailableRow(board, col);
        if (row != null) {
          Board virtualBoard = Utils.cloneBoard(board);
          virtualBoard.setField(row, col, PLAYER_PIECE);
          Double score = miniMax(virtualBoard, depth - 1, a, b, true).getSecond();
          if (score < value) {
            value = score;
            column = col;
          }
          b = Math.min(b, value);
          if (a >= b) {
            break;
          }
        }
      }
      return new Pair<>(column, value);
    }
  }

  /**
   * Amalgamates the scores for all directional checks.
   *
   * @param board     Board object
   * @param playerNum player number
   * @return a total score for the given position.
   */
  private int scorePosition(Board board, int playerNum) {
    int s1 = scorePositionCentral(board, playerNum);
    int s2 = scorePositionVertical(board, playerNum);
    int s3 = scorePositionDiagonalB(board, playerNum);
    int s4 = scorePositionDiagonalF(board, playerNum);
    int s5 = scorePositionHorizontal(board, playerNum);
    return s1 + s2 + s3 + s4 + s5;
  }

  /**
   * Calculates the score for this central column.
   *
   * @param board     Board object
   * @param playerNum player number
   * @return score for this direction
   */
  private int scorePositionCentral(Board board, int playerNum) {
    int score = 0;
    int central = board.getWidth() / 2;
    for (int row = 0; row < board.getHeight(); row++) {
      if (board.getField(row, central) == playerNum) {
        score++;
      }
    }
    return score * 3;
  }

  /**
   * Calculates the score for vertical positions.
   *
   * @param board     Board object
   * @param playerNum player number
   * @return score for this direction
   */
  private int scorePositionVertical(Board board, int playerNum) {
    int score = 0;
    for (int row = 0; row < board.getHeight() - (connectN - 1); row++) {
      for (int col = 0; col < board.getWidth(); col++) {
        for (int i = 0; i < connectN; i++) {
          window[i] = board.getField(row + i, col);
        }
        score += evaluateWindow(window, playerNum);
      }
    }
    return score;
  }

  /**
   * Calculates the score for diagonal (backward) positions.
   *
   * @param board     Board object
   * @param playerNum player number
   * @return score for this direction
   */
  private int scorePositionDiagonalB(Board board, int playerNum) {
    int score = 0;
    for (int row = 0; row < board.getHeight() - (connectN - 1); row++) {
      for (int col = 0; col < board.getWidth() - (connectN - 1); col++) {
        for (int i = 0; i < connectN; i++) {
          window[i] = board.getField(row + i, col + i);
        }
        score += evaluateWindow(window, playerNum);
      }
    }
    return score;
  }

  /**
   * Calculates the score for diagonal (forward) positions.
   *
   * @param board     Board object
   * @param playerNum player number
   * @return score for this direction
   */
  private int scorePositionDiagonalF(Board board, int playerNum) {
    int score = 0;
    for (int row = 0; row < board.getHeight() - (connectN - 1); row++) {
      for (int col = 0; col < board.getWidth() - (connectN - 1); col++) {
        for (int i = 0; i < connectN; i++) {
          window[i] = board.getField(row + (connectN - 1) - i, col + i);
        }
        score += evaluateWindow(window, playerNum);
      }
    }
    return score;
  }

  /**
   * Calculates the score for horizontal positions.
   *
   * @param board     Board object
   * @param playerNum player number
   * @return score for this direction
   */
  private int scorePositionHorizontal(Board board, int playerNum) {
    int score = 0;
    for (int row = 0; row < board.getHeight(); row++) {
      for (int col = 0; col < board.getWidth() - (connectN - 1); col++) {
        for (int i = 0; i < connectN; i++) {
          window[i] = board.getField(row, col + i);
        }
        score += evaluateWindow(window, playerNum);
      }
    }
    return score;
  }

  /**
   * Calculates the score for a small board fragment.
   *
   * @param win       board fragment
   * @param playerNum player number
   * @return score for this board fragment
   */
  private int evaluateWindow(Integer[] win, int playerNum) {
    int score = 0;
    int opponent = playerNum;
    if (playerNum == PLAYER_PIECE) {
      opponent = CPU_PIECE;
    }
    int countEmpty = 0;
    int countOwned = 0;
    int countOppon = 0;
    for (int i = 0; i < connectN; i++) {
      if (win[i] == 0) {
        countEmpty++;
      } else if (win[i] == opponent) {
        countOwned++;
      } else {
        countOppon++;
      }
    }
    if (countEmpty + countOwned + countOppon != connectN) {
      throw new IllegalStateException("Counts don't add up!");
    }
    if (countOwned == 4) {
      score += 100;
    } else if (countOwned == 3 && countEmpty == 1) {
      score += 5;
    } else if (countOwned == 2 && countEmpty == 2) {
      score += 2;
    }
    if (countOppon == 3 && countEmpty == 1) {
      score -= 4;
    }
    return score;
  }

}
