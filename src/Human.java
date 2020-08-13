import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Human player.
 */
public class Human extends Player {

  // console input reader
  BufferedReader reader;
  // player number
  public Integer playerNum;


  /**
   * Constructor.
   *
   * @param playerNum player number.
   */
  public Human(int playerNum) {
    this.playerNum = playerNum;
    this.reader = new BufferedReader(new InputStreamReader(System.in));
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
   * Makes a move on board using the column typed in the console.
   *
   * @param board Board object
   * @return column index selected by the user
   */
  public Integer makeMove(Board board) {
    Integer validColumn = null;
    Integer inputColumn;
    do {
      board.printBoard();
      inputColumn = getInputColumn(board.getWidth());
      if (Gmaster.isIllegalMove(board, inputColumn)) {
        System.out.println("This column is full! Please select a different column.");
      } else {
        validColumn = inputColumn;
      }
    } while (validColumn == null);
    return validColumn;
  }

  /**
   * Iterates until the valid (existing) column index can be retrieved from the console.
   *
   * @param boardWidth number of columns on the board
   * @return column index selected by user
   */
  private Integer getInputColumn(int boardWidth) {
    Integer inputColumn;
    do {
      String consoleInput = getConsoleInput(boardWidth);
      inputColumn = Console.getColumnFromInput(consoleInput, boardWidth);
    } while (inputColumn == null);
    return inputColumn;
  }


  /**
   * Get the user input from the console.
   *
   * @param boardWidth number of columns on the board
   * @return a string with user input from the console.
   */
  private String getConsoleInput(int boardWidth) {
    String upperLetter = Console.getLetterFromIndex(boardWidth - 1);
    System.out.printf("Please select a column (A - %s):\n", upperLetter);
    try {
      String consoleInput = reader.readLine();
      if (consoleInput == null) {
        System.exit(0);
      }
      return consoleInput;
    } catch (IOException ex) {
      System.err.println(ex.getMessage());
      System.exit(1);
    }
    return null;
  }


}
