package model.board
import scala.collection.mutable.ArrayBuffer
import model.card.unitcard.*

/** Represents a combat row on the player's side of the board.
 *
 * A combat row stores all the unit cards placed in a specific lane
 * (Melee, Ranged, or Siege).
 */

class CombatRow extends Row:
  /** Stores all the unit cards currently placed in this combat row. */
  private val units: ArrayBuffer[UnitCard] = ArrayBuffer.empty[UnitCard]

  /** Returns all the cards in this combat row. */
  def getCards: Seq[UnitCard] = units.toSeq

  /** Adds a new unit card to the combat row. */
  def addCard(card: UnitCard): Unit =
    units.append(card)

  /** Sets the strength of every card in the row to 1. */
  def setToOne(): Unit = units.foreach(unitCard => unitCard.setStrength(1))

  def getTotalStrength: Int = units.map(unitCard => unitCard.getStrength).sum