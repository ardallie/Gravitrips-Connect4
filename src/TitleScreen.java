public class TitleScreen {


  /**
   * Displays the Connect N logo on game start
   * https://manytools.org/hacker-tools/ascii-banner/
   */
  public static void printTitleScreen(int connectN) {
    String[] title = getTitleName();
    String[][] digits = getTitleDigits();
    System.out.println();
    System.out.printf(" %s%s \n", title[0], digits[connectN - 3][0]);
    System.out.printf(" %s%s \n", title[1], digits[connectN - 3][1]);
    System.out.printf(" %s%s \n", title[2], digits[connectN - 3][2]);
    System.out.printf(" %s%s \n", title[3], digits[connectN - 3][3]);
    System.out.printf(" %s%s \n", title[4], digits[connectN - 3][4]);
    System.out.printf(" %s%s \n", title[5], digits[connectN - 3][5]);
    System.out.printf(" %s%s \n", title[6], digits[connectN - 3][6]);
    System.out.printf(" %s%s \n", title[7], digits[connectN - 3][7]);
    System.out.println();
  }


  /**
   * Game logo: CONNECT.
   *
   * @return game logo
   */
  private static String[] getTitleName() {
    return new String[]{
        ":'######:::'#######::'##::: ##:'##::: ##:'########::'######::'########::::",
        "'##... ##:'##.... ##: ###:: ##: ###:: ##: ##.....::'##... ##:... ##..:::::",
        " ##:::..:: ##:::: ##: ####: ##: ####: ##: ##::::::: ##:::..::::: ##:::::::",
        " ##::::::: ##:::: ##: ## ## ##: ## ## ##: ######::: ##:::::::::: ##:::::::",
        " ##::::::: ##:::: ##: ##. ####: ##. ####: ##...:::: ##:::::::::: ##:::::::",
        " ##::: ##: ##:::: ##: ##:. ###: ##:. ###: ##::::::: ##::: ##:::: ##:::::::",
        ". ######::. #######:: ##::. ##: ##::. ##: ########:. ######::::: ##:::::::",
        ":......::::.......:::..::::..::..::::..::........:::......::::::..::::::::",
    };
  }


  /**
   * Game logo: N value.
   *
   * @return 2d array of all possible N values.
   */
  private static String[][] getTitleDigits() {
    return new String[][]{
        {
            ":'#######::",
            "'##.... ##:",
            "..::::: ##:",
            ":'#######::",
            ":...... ##:",
            "'##:::: ##:",
            ". #######::",
            ":.......:::"
        },
        {
            "'##::::::::",
            " ##:::'##::",
            " ##::: ##::",
            " ##::: ##::",
            " #########:",
            "...... ##::",
            ":::::: ##::",
            "::::::..:::",
        },
        {
            "'########:",
            " ##.....::",
            " ##:::::::",
            " #######::",
            "...... ##:",
            "'##::: ##:",
            ". ######::",
            ":......:::",
        },
        {
            ":'#######::",
            "'##.... ##:",
            " ##::::..::",
            " ########::",
            " ##.... ##:",
            " ##:::: ##:",
            ". #######::",
            ":.......:::",
        }
    };
  }

}