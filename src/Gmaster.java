import java.util.ArrayList;

/**
 * Game manager responsible for enforcing the game rules.
 */
public class Gmaster {

  /**
   * Get the row number in a column where the next piece can land on.
   *
   * @param board Board object
   * @param col   column number
   * @return the available row number, null if all slots are taken
   */
  public static Integer getAvailableRow(Board board, int col) {
    if (isIllegalMove(board, col)) {
      return null;
    }
    int row = 0;
    while (row < board.getHeight() - 1 && board.getField(row + 1, col) == 0) {
      row++;
    }
    return row;
  }

  /**
   * Get the index numbers of available columns.
   *
   * @param board Board object
   * @return array of available columns
   */
  public static ArrayList<Integer> getAvailableColumns(Board board) {
    ArrayList<Integer> columns = new ArrayList<>();
    for (int col = 0; col < board.getWidth(); col++) {
      if (board.getField(0, col) == 0) {
        columns.add(col);
      }
    }
    return columns;
  }

  /**
   * Check if the last node has been reached.
   *
   * @param board    Board object
   * @param connectN number of pieces required to win
   * @return true if the last node is reached
   */
  public static boolean isTerminalNode(Board board, int connectN) {
    boolean winPlayer1 = isWinner(board, 1, connectN);
    boolean winPlayer2 = isWinner(board, 2, connectN);
    boolean isBoardFull = isBoardFull(board);
    return winPlayer1 || winPlayer2 || isBoardFull;
  }

  /**
   * Checks if the player achieved a winning position on the board
   * by performing horizontal, vertical and diagonal board scan.
   *
   * @param board    Board object
   * @param player   player number
   * @param connectN number of pieces required to win
   * @return true if given player has a winning position, false if not
   */
  public static boolean isWinner(Board board, int player, int connectN) {
    boolean winH = winHorizontal(board, player, connectN);
    boolean winV = winVertical(board, player, connectN);
    boolean winB = winDiagonalB(board, player, connectN);
    boolean winF = winDiagonalF(board, player, connectN);
    return winH || winV || winB || winF;
  }

  /**
   * Check if the board is full and cannot take any more chips.
   *
   * @param board Board object
   * @return true if the board is full, false if not
   */
  public static boolean isBoardFull(Board board) {
    for (int col = 0; col < board.getWidth(); col++) {
      if (!isIllegalMove(board, col)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Check if a column is filled up and cannot accept any more pieces.
   *
   * @param board Board object
   * @param col   column number
   * @return true if the move cannot be made in the given column, false if is legal to place
   */
  public static boolean isIllegalMove(Board board, int col) {
    return board.getField(0, col) != 0;
  }

  /**
   * Scans the board for a horizontal win pattern.
   *
   * @param board    Board object
   * @param player   player number
   * @param connectN number of pieces required to win
   * @return true if a win pattern is found, false if not
   */
  public static boolean winHorizontal(Board board, int player, int connectN) {
    for (int row = 0; row < board.getHeight(); row++) {
      for (int col = 0; col < board.getWidth() - (connectN - 1); col++) {
        int winner = 0;
        for (int i = 0; i < connectN; i++) {
          winner += board.getField(row, col + i) == player ? 1 : 0;
        }
        if (winner == connectN) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Scans the board for a vertical win pattern.
   *
   * @param board    Board object
   * @param player   player number
   * @param connectN number of pieces required to win
   * @return true if a win pattern is found, false if not
   */
  public static boolean winVertical(Board board, int player, int connectN) {
    for (int row = 0; row < board.getHeight() - (connectN - 1); row++) {
      for (int col = 0; col < board.getWidth(); col++) {
        int winner = 0;
        for (int i = 0; i < connectN; i++) {
          winner += board.getField(row + i, col) == player ? 1 : 0;
        }
        if (winner == connectN) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Scans the board for a diagonal \ win pattern.
   *
   * @param board    Board object
   * @param player   player number
   * @param connectN number of pieces required to win
   * @return true if a win pattern is found, false if not
   */
  public static boolean winDiagonalB(Board board, int player, int connectN) {
    for (int row = 0; row < board.getHeight() - (connectN - 1); row++) {
      for (int col = 0; col < board.getWidth() - (connectN - 1); col++) {
        int winner = 0;
        for (int i = 0; i < connectN; i++) {
          winner += board.getField(row + i, col + i) == player ? 1 : 0;
        }
        if (winner == connectN) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Scans the board for a diagonal / win pattern.
   *
   * @param board    Board object
   * @param player   player number
   * @param connectN number of pieces required to win
   * @return true if a win pattern is found, false if not
   */
  public static boolean winDiagonalF(Board board, int player, int connectN) {
    for (int col = (connectN - 1); col < board.getHeight(); col++) {
      for (int row = 0; row < board.getWidth() - (connectN - 1); row++) {
        int winner = 0;
        for (int i = 0; i < connectN; i++) {
          winner += board.getField(col - i, row + i) == player ? 1 : 0;
        }
        if (winner == connectN) {
          return true;
        }
      }
    }
    return false;
  }

}
