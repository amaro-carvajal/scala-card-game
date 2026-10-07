package model.player

import model.card.*
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/** Represents a player's deck of cards.
 *
 * @param cards the initial sequence of cards that make up the deck
 */

class Deck (private val cards: ArrayBuffer[Card]):
  /** Draws the top card from the deck.
   *
   * Removes and returns the first card in the list if available.
   * If the deck is empty, returns `None`.
   *
   * @return an Option containing the drawn card, or None if the deck is empty
   */
  def drawCard(): Option[Card] =
    if cards.nonEmpty then
      val card = cards.remove(0)
      Some(card)
    else
      None

  /** Gets the currently cards in the deck.
   *
   * @return the currently cards in the deck.
   */
  def getCards: Seq[Card] = cards.toSeq

  /** Updates the current deck. */
  def setCards(newDeck: Seq[Card]): Unit =
    cards.clear()
    cards.appendAll(newDeck)

  def shuffle(): Unit =
    val currentCards = cards.toSeq
    val shuffledCards = Random.shuffle(currentCards)
    setCards(shuffledCards)
