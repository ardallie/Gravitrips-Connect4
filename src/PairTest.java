import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PairTest {

  @Test
  void GetPairTest1() {
    Pair<Integer, Double> pair = new Pair<>(6, 37.5);
    Assertions.assertEquals(6, pair.getFirst());
    Assertions.assertEquals(37.5, pair.getSecond());
  }

  @Test
  void GetPairTest2() {
    Pair<Integer, Double> pair = new Pair<>(1999, Double.NEGATIVE_INFINITY);
    Assertions.assertEquals(1999, pair.getFirst());
    Assertions.assertEquals(Double.NEGATIVE_INFINITY, pair.getSecond());
  }

  @Test
  void GetPairTest3() {
    Pair<Integer, Double> pair = new Pair<>(1999, Double.POSITIVE_INFINITY);
    Assertions.assertEquals(1999, pair.getFirst());
    Assertions.assertEquals(Double.POSITIVE_INFINITY, pair.getSecond());
  }


}


