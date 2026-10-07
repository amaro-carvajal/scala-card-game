package model.card

import model.board.*

/** Defines the fundamental contract for any object that is considered a "Card"
 * in the game.
 */

trait Card:
  /** Gets the name of the card.
   *
   * @return the card's name
   */
  def getName: String

  /** Checks equality between this card and another object.
   *
   * @param obj the object to compare with
   * @return `true` if the objects are equal, `false` otherwise
   */
  def equals(obj: Any): Boolean

  /** Compares this card with another card for ordering purposes.
   *
   * @param that the card to compare with
   * @return a negative integer, zero, or a positive integer as this card
   *         is less than, equal to, or greater than the specified card
   */
  def compare(that: Card): Int =
    this.getName.compare(that.getName)

  /** Defines how the card is played on the board.
   *
   * Default implementation does nothing. Subclasses should override this method
   * to specify their unique placement or effect behavior.
   *
   * @param board the game board where the card is played
   * @param area  the player’s area in which the card is placed
   */
  def playOnBoard(board: Board, area: PlayerArea): Unit = ()
