public class Player
{

  boolean isGoing;

  /**
   * constructs a player object representing the players
   * @param isGoing the determinant for when it is the players turn
   */
  public Player(boolean isGoing)
  {
    this.isGoing = isGoing;
  }

  /**
   * ends the player's turn if they went, starts if they haven't
   */
  public void transition()
  {
    isGoing = !isGoing;
  }
}