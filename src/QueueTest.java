import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;


class QueueTest {

  @Test
  void isEmpty() {
    Queue<String> queue = new Queue<String>();
    Assertions.assertTrue(queue.isEmpty());
    queue.enqueue("Item A");
    Assertions.assertFalse(queue.isEmpty());
  }

  @Test
  void size() {
    Queue<String> queue = new Queue<String>();
    Assertions.assertEquals(0, queue.size());
    queue.enqueue("Item A");
    queue.enqueue("Item B");
    queue.enqueue("Item C");
    Assertions.assertEquals(3, queue.size());
  }

  @Test
  void enqueue() {
    Queue<String> queue = new Queue<String>();
    queue.enqueue("Item A");
    queue.enqueue("Item B");
    queue.enqueue("Item C");
    Assertions.assertEquals(3, queue.size());
  }

  @Test
  void dequeue() {
    Queue<String> queue = new Queue<String>();

    // add items
    queue.enqueue("Item A");
    queue.enqueue("Item B");
    queue.enqueue("Item C");

    // remove items
    Assertions.assertEquals(3, queue.size());

    Object itemA = queue.dequeue();
    Assertions.assertEquals("Item A", itemA);
    Assertions.assertEquals(2, queue.size());

    Object itemB = queue.dequeue();
    Assertions.assertEquals("Item B", itemB);
    Assertions.assertEquals(1, queue.size());

    Object itemC = queue.dequeue();
    Assertions.assertEquals("Item C", itemC);
    Assertions.assertEquals(0, queue.size());

    // attempt to remove element from empty collection
    Assertions.assertThrows(NoSuchElementException.class, queue::dequeue);
  }

  @Test
  void iterator() {
    Queue<String> queue = new Queue<String>();

    // add items
    queue.enqueue("Item A");
    queue.enqueue("Item B");
    queue.enqueue("Item C");

    int index = 0;
    String[] arr = new String[]{"Item A", "Item B", "Item C"};
    for (String item : queue) {
      Assertions.assertEquals(arr[index], item);
      index++;
    }
  }

}