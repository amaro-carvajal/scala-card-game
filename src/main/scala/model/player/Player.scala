package model.player

import model.card.Card
import model.board.*

/** Represent a Player.
 * @param name Name of the player.
 * @param area Name of the board section of the player. (This is momentary,
 * as the board has not been implemented yet, so I don't know how to treat the board section.)
 *
 * @constructor Creates a new player with his name and board section. */

class Player (private val name: String, private val area: PlayerArea, private val deck: Deck, private val hand: Hand) extends TPlayer {
  private var passed: Boolean = false

  def setPassed(value: Boolean): Unit = {
    passed = value
  }

  def hasPassed: Boolean = passed

  def resetPassed(): Unit = {
    passed = false
  }
  /** Gems of the player. */
  private var gems: Int = 2

  /** Gets the name of the player.
   *
   * @return the player's name
   */
  override def getName: String = name

  /** Gets the area of the player.
   *
   * @return the player's area
   */
  override def getArea: PlayerArea = area

  /** Gets the gems of the player.
   *
   * @return the player's gems
   */
  override def getGems: Int = gems

  /** Gets the hand of the player.
   *
   * @return the player's hand
   */
  override def getHand: Seq[Card] = hand.getCards

  /** Updates the player’s current hand.*/
  override def setHand(newHand: Seq[Card]) : Unit =
    hand.setCards(newHand)

  /** Gets the deck of the player.
   *
   * @return the player's deck
   */
  override def getDeck: Seq[Card] = deck.getCards

  /** Updates the player’s current deck.*/
  override def setDeck(newDeck: Seq[Card]): Unit = deck.setCards(newDeck)

  /** Removes one gem from the player, typically after losing a round. */
  override def loseGem (): Unit =
    if gems > 0 then 
      gems-=1
      
  override def setGems(newGems: Int): Unit = 
    gems = newGems

  /** Plays a card from the player's hand onto the game board.
   *
   * @param card the card being played
   * @param board the game board where the card is played
   */
  override def playACard (card: Card, board: Board): Unit =
    if hand.removeCard(card) then
      card.playOnBoard(board, area)

  /** Draws a card from the player's deck into their hand. */
  override def drawCard (): Unit =
    deck.drawCard().foreach(card => hand.addCard(card))
    
  override def getTotalStrengthOfHand: Int = hand.getTotalStrength
}
