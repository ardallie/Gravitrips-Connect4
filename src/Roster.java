/**
 * Player roster implemented with a Queue ADT.
 */
public class Roster extends Queue<Player> {

  /**
   * Manage the player roster through the Queue ADT.
   * Next player is removed from the queue (to make a move) then added back to the end.
   *
   * @return Player object that is to make a move next
   */
  public Player nextPlayer() {
    // remove the next player from the queue
    Player player = this.dequeue();
    // add this player back to the end of a queue
    this.enqueue(player);
    return player;
  }

}
