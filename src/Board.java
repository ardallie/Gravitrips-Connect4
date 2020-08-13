import java.util.Arrays;

/**
 * Representation of the `Connect 4` game board.
 */
public class Board {

  // 2d array representing the board
  private Integer[][] board;
  // Unicode symbols representing players, accessed by the array index
  // [0]: empty, [1]: player 1, [2]: player 2 etc.
  private final String[] symbols = new String[]{".", "X", "*", "@"};

  /**
   * Constructor.
   * Performs the width and height checks, initialises and clears the board.
   *
   * @param boardWidth  number of columns on the board
   * @param boardHeight number of rows on the board
   */
  public Board(int boardWidth, int boardHeight) {
    if (boardWidth < 6 || boardWidth > 12) {
      throw new IllegalArgumentException("Board width must be between 6 and 12.");
    }
    if (boardHeight < 6 || boardHeight > 10) {
      throw new IllegalArgumentException("Board height must be between 6 and 10.");
    }
    this.board = new Integer[boardHeight][boardWidth];
    this.clearBoard();
  }

  /**
   * Gets a value from the board.
   *
   * @param row row index
   * @param col column index
   * @return corresponding value from the board
   */
  public int getField(int row, int col) {
    return board[row][col];
  }

  /**
   * Sets the value on the board.
   *
   * @param row row index
   * @param col column index
   * @param val value to be set
   */
  public void setField(int row, int col, int val) {
    this.board[row][col] = val;
  }

  /**
   * Get board height.
   *
   * @return number of rows on board
   */
  public int getHeight() {
    return board.length;
  }

  /**
   * Get board width.
   *
   * @return number of columns on board
   */
  public int getWidth() {
    if (board.length == 0) {
      throw new IndexOutOfBoundsException();
    }
    return board[0].length;
  }

  /**
   * Clears the entire board by filling the 2d array with zeroes.
   */
  public void clearBoard() {
    for (Integer[] row : board) {
      Arrays.fill(row, 0);
    }
  }

  /**
   * Print the column labels (A B C D E F G).
   */
  private void printLabels() {
    System.out.print("       ");
    for (int col = 0; col < getWidth(); col++) {
      System.out.print(Console.getAlphabet().charAt(col) + " ");
    }
    System.out.println();
  }

  /**
   * Get the character that represents the player.
   *
   * @param playerNum player number
   * @return symbol representing the player
   */
  public String getPlayerSymbol(int playerNum) {
    return symbols[playerNum];
  }

  /**
   * Print the board to the console.
   */
  public void printBoard() {
    for (int row = 0; row < getHeight(); row++) {
      System.out.print("       ");
      for (int col = 0; col < getWidth(); col++) {
        // pick up a symbol from array using the player name as an index
        // zero index is reserved for an empty space
        System.out.print(symbols[getField(row, col)]);
        System.out.print(" ");
      }
      System.out.println();
    }
    printLabels();
    System.out.println();
  }
}
