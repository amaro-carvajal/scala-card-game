package model.player

import model.card.*
import scala.collection.mutable.ArrayBuffer
import model.card.unitcard.*

/** Represents a player's hand of cards.
 */

class Hand:
  /** Internal buffer holding all cards currently in the hand. */
  private val cards: ArrayBuffer[Card] = ArrayBuffer.empty[Card]

  /** Gets the currently cards in the hand.
   *
   * @return the currently cards in the hand.
   */
  def getCards: Seq[Card] = cards.toSeq

  /** Replaces the entire hand with a new set of cards.
   *
   * @param newHand the new sequence of cards to assign as the player's hand
   */
  def setCards(newHand: Seq[Card]): Unit =
    cards.clear()
    cards.appendAll(newHand)

  /** Removes a specific card from the hand.
   *
   * @param card the card to remove
   * @return true if the card was successfully removed, false if it was not found
   */
  def removeCard(card: Card): Boolean =
    val idx = cards.indexOf(card)
    if idx != -1 then
      cards.remove(idx)
      true
    else
      false

  /** Adds a new card to the player's hand.
   *
   * @param card the card to add
   */
  def addCard(card: Card): Unit =
    cards += card

  def getTotalStrength: Int =
    val units = this.getCards.filter(_.isInstanceOf[UnitCard])
    val totalStrength = units.map(_.asInstanceOf[UnitCard].getStrength).sum
    totalStrength
