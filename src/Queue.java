import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * FIFO Queue data type built with a linked list.
 * Implementation based on Sedgewick R., Wayne K., Algorithms 4th edn., pp. 149 - 151
 * https://algs4.cs.princeton.edu/13stacks/Queue.java.html
 *
 * @param <Item> Object type to be stored
 */
public class Queue<Item> implements Iterable<Item> {

  private Node first;
  private Node last;
  private int len;

  private class Node {
    // nested class to define Nodes
    Item item;
    Node next;
  }

  /**
   * Constructor.
   */
  public Queue() {
  }

  /**
   * Checks if the queue is empty.
   *
   * @return true if empty, false if not
   */
  public boolean isEmpty() {
    return first == null;
  }

  /**
   * Checks the number of items.
   *
   * @return number of items in queue
   */
  public int size() {
    return len;
  }

  /**
   * Adds the item to the back of the queue (FIFO).
   *
   * @param item element to be added
   */
  public void enqueue(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("The item cannot be null");
    }
    Node oldLast = last;
    last = new Node();
    last.item = item;
    last.next = null;
    if (isEmpty()) {
      first = last;
    } else {
      oldLast.next = last;
    }
    len++;
  }

  /**
   * Remove and return the item from the front of the queue (FIFO).
   *
   * @return element from the front
   */
  public Item dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException("There are no elements in collection");
    }
    Item item = first.item;
    first = first.next;
    len--;
    if (isEmpty()) {
      last = null;
    }
    return item;
  }

  /**
   * Returns an iterator over items in order from front to back.
   *
   * @return iterator object
   */
  public Iterator<Item> iterator() {
    return new ListIterator();
  }

  private class ListIterator implements Iterator<Item> {
    private Node current = first;

    public boolean hasNext() {
      return current != null;
    }

    public void remove() {
      throw new UnsupportedOperationException("Remove operation is not supported.");
    }

    public Item next() {
      if (isEmpty()) {
        throw new NullPointerException("There are no elements in collection");
      }
      Item item = current.item;
      current = current.next;
      return item;
    }
  }

}