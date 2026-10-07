package model.player

import model.card.*
import model.board.*

/** Defines the contract for a player within the game.
 */

trait TPlayer {
  /** Draws a card from the player's deck into their hand. */
  def drawCard(): Unit

  /** Plays a card from the player's hand onto the game board.
   *
   * @param card the card being played
   * @param board the game board where the card is played
   */
  def playACard(card: Card, board: Board): Unit

  /** Removes one gem from the player, typically after losing a round. */
  def loseGem(): Unit

  def setGems(newGems: Int): Unit

  /** Gets the gems of the player.
   *
   * @return the player's gems
   */
  def getGems: Int

  /** Gets the name of the player.
   *
   * @return the player's name
   */
  def getName: String

  /** Gets the hand of the player.
   *
   * @return the player's hand
   */
  def getHand: Seq[Card]

  /** Gets the deck of the player.
   *
   * @return the player's deck
   */
  def getDeck: Seq[Card]

  /** Gets the area of the player.
   *
   * @return the player's area
   */
  def getArea: PlayerArea

  /** Updates the player’s current hand.*/
  def setHand(newHand: Seq[Card]): Unit

  /** Updates the player’s current deck.*/
  def setDeck(newDeck: Seq[Card]): Unit
  
  def getTotalStrengthOfHand: Int
}
