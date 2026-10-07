package model.card.unitcard

import model.board.PlayerArea
import model.card.*
import model.card.unitcard.uniteffect.UnitEffect
import model.card.unitcard.unittype.UnitType
import model.board.*

/** Defines the contract for any object that is considered a "Unit Card" within the game.
 */
trait UnitCard extends Card:
  /** Indicates whether this weather card has already been played. */
  var wasPlayed: Boolean = false

  /** Gets the unit type of the card.
   *
   * @return the card's unite type
   */
  def getUnitType: UnitType

  /** Gets the effect of the card.
   *
   * @return the card's effect
   */
  def getEffect: Option[UnitEffect]

  /** Gets the strength of the card.
   *
   * @return the card's strength
   */
  def getStrength: Int

  /** Sets the strength value of the card.
   *
   * @param newStrength the new strength value to assign to the card
   */
  def setStrength(newStrength: Int): Unit

  /** Plays this unit card on the board.
   *
   * When played, the card is placed in the correct row according to its unit type.
   * It is then marked as played, and any associated effect is automatically applied.
   *
   * @param board the game board on which the card is played
   * @param area  the player area where the card is placed
   */
  override def playOnBoard(board: Board, area: PlayerArea): Unit =
    this.getUnitType.placeOn(area, this)
    this.wasPlayed = true
    this.getEffect.foreach(_.applyEffect(board, this))