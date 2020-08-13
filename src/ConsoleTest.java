import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConsoleTest {

  @Test
  void validateConsoleInputTest1() {
    Assertions.assertTrue(Console.validateConsoleInput("A", 7));
    Assertions.assertTrue(Console.validateConsoleInput("B", 7));
    Assertions.assertTrue(Console.validateConsoleInput("C", 7));
    Assertions.assertTrue(Console.validateConsoleInput("D", 7));
    Assertions.assertTrue(Console.validateConsoleInput("E", 7));
    Assertions.assertTrue(Console.validateConsoleInput("F", 7));
    Assertions.assertTrue(Console.validateConsoleInput("G", 7));
  }

  @Test
  void validateConsoleInputTest2() {
    Assertions.assertFalse(Console.validateConsoleInput("", 7));
    Assertions.assertFalse(Console.validateConsoleInput("H", 7));
    Assertions.assertFalse(Console.validateConsoleInput("I", 7));
    Assertions.assertFalse(Console.validateConsoleInput("J", 7));
    Assertions.assertFalse(Console.validateConsoleInput("AA", 7));
  }

  @Test
  void getPositionTest() {
    Assertions.assertEquals(0, Console.getColumnFromInput("a", 7));
    Assertions.assertEquals(0, Console.getColumnFromInput("A", 7));
    Assertions.assertEquals(1, Console.getColumnFromInput("b", 7));
    Assertions.assertEquals(1, Console.getColumnFromInput("B", 7));
    Assertions.assertEquals(4, Console.getColumnFromInput("e", 7));
    Assertions.assertEquals(5, Console.getColumnFromInput("F", 7));
    Assertions.assertEquals(2, Console.getColumnFromInput("  c", 7));
    Assertions.assertEquals(2, Console.getColumnFromInput(" c  ", 7));
    Assertions.assertEquals(2, Console.getColumnFromInput("c    ", 7));
  }

  @Test
  void getPositionTest2() {
    Assertions.assertNull(Console.getColumnFromInput("", 7));
    Assertions.assertNull(Console.getColumnFromInput("a0", 7));
    Assertions.assertNull(Console.getColumnFromInput("H", 7));
    Assertions.assertNull(Console.getColumnFromInput("II", 7));
    Assertions.assertNull(Console.getColumnFromInput("Q", 7));
    Assertions.assertNull(Console.getColumnFromInput("hg", 7));
  }

}


