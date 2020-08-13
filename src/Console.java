import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parsing the user input from the console.
 */
public class Console {

  private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

  /**
   * Get the alphabet letters.
   *
   * @return the string containing alphabet letters
   */
  public static String getAlphabet() {
    return alphabet;
  }

  /**
   * Check if the argument is an integer within the defined range (e.g. 3 to 6)
   *
   * @param arg command-line argument
   * @param min minimum allowed value
   * @param max maximum allowed value
   * @return true if the argument is valid, false if not
   */
  public static boolean validateArgument(String arg, int min, int max) {
    Pattern pattern = Pattern.compile("[" + min + "-" + max + "]"); // e.g. [3-6]
    Matcher matcher = pattern.matcher(arg);
    return matcher.find();
  }

  /**
   * Get the connectN parameter from the command line.
   *
   * @param cliArg command line argument
   * @return connectN parameter, defaults to 4 if not valid
   */
  public static int getCliConnectN(String cliArg) {
    return Console.validateArgument(cliArg, 3, 6) ? Integer.parseInt(cliArg) : 4;
  }

  /**
   * Get the players parameter from the command line.
   *
   * @param cliArg command line argument
   * @return players parameter, defaults to 2 if not valid
   */
  public static int getCliPlayers(String cliArg) {
    return Console.validateArgument(cliArg, 2, 3) ? Integer.parseInt(cliArg) : 3;
  }

  /**
   * Get the letter corresponding to the given index.
   *
   * @param index position of the letter
   * @return a letter corresponding to the index
   */
  public static String getLetterFromIndex(int index) {
    return String.valueOf(alphabet.charAt(index));
  }

  /**
   * Validate the user input using regular expressions.
   *
   * @param consoleInput user input string.
   * @param boardWidth   number of columns on the board
   * @return true if the user input is valid, false if not
   */
  public static boolean validateConsoleInput(String consoleInput, int boardWidth) {
    String letters = alphabet.substring(0, boardWidth);
    Pattern pattern = Pattern.compile("[" + letters + "]");
    Matcher matcher = pattern.matcher(consoleInput);
    return matcher.find() && consoleInput.length() == 1;
  }

  /**
   * Convert the selected letter to a column number.
   *
   * @param consoleInput user input string.
   * @param boardWidth   number of columns on the board
   * @return the column number converted from the corresponding letter
   */
  public static Integer getColumnFromInput(String consoleInput, int boardWidth) {
    String cleanInput = consoleInput.toUpperCase().replace(" ", "");
    if (validateConsoleInput(cleanInput, boardWidth)) {
      return cleanInput.charAt(0) - "A".charAt(0);
    }
    if (!consoleInput.equals("")) {
      System.out.print("Incorrect input. ");
    }
    return null;
  }

}
