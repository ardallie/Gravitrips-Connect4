public class Utils {

  /**
   * Copies the board values to a new object.
   *
   * @param src Board object
   * @return a new board object, which is a copy of the source board
   */
  public static Board cloneBoard(Board src) {
    int width = src.getWidth();
    int height = src.getHeight();
    Board clone = new Board(width, height);
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        clone.setField(row, col, src.getField(row, col));
      }
    }
    return clone;
  }

}