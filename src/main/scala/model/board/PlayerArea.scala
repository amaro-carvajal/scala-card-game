package model.board
import model.card.unitcard.*
import model.card.unitcard.unittype.*

/** Represents a player's side of the game board.
 */

class PlayerArea:
  /** The row that holds all melee unit cards. */
  private val melee_row: CombatRow = new CombatRow()

  /** The row that holds all ranged unit cards. */
  private val ranged_row: CombatRow = new CombatRow()

  /** The row that holds all siege unit cards. */
  private val siege_row: CombatRow = new CombatRow()

  /** Returns the melee row */
  def getMeleeRow: CombatRow = melee_row

  /** Returns the ranged row */
  def getRangedRow: CombatRow = ranged_row

  /** Returns the siege row */
  def getSiegeRow: CombatRow = siege_row

  /** Places a unit card in the melee row. */
  def placeUnit(card: UnitCard, unitType: Melee): Unit =
    melee_row.addCard(card)

  /** Places a unit card in the ranged row. */
  def placeUnit(card: UnitCard, unitType: Ranged): Unit =
    ranged_row.addCard(card)

  /** Places a unit card in the siege row. */
  def placeUnit(card: UnitCard, unitType: Siege): Unit =
    siege_row.addCard(card)

  /** Applies the fog weather effect*/
  def applyFogEffect(): Unit =
    ranged_row.setToOne()

  /** Applies the frost weather effect*/
  def applyFrostEffect(): Unit =
    melee_row.setToOne()

  /** Applies the rain weather effect */
  def applyRainEffect(): Unit =
    siege_row.setToOne()

  /** Checks whether a given unit card belongs to this player area. */
  def contains(card: UnitCard): Boolean =
    melee_row.getCards.contains(card) || ranged_row.getCards.contains(card) || siege_row.getCards.contains(card)

  /** Returns the combat row corresponding to the given unit type. */
  def getRow(unitType: UnitType): CombatRow = unitType.getRow(this)

  def getTotalStrength: Int =
    melee_row.getTotalStrength + ranged_row.getTotalStrength + siege_row.getTotalStrength
