package model.card.unitcard.unittype

import model.board.*
import model.card.*
import model.card.unitcard.UnitCard

/** Represents the Siege deployment type. */

class Siege extends UnitType:
  /** Places the given unit card in the siege row of the player area.
   *
   * @param area the player area where the card will be placed
   * @param card the unit card being placed
   */
  override def placeOn(area: PlayerArea, card: UnitCard): Unit =
    area.placeUnit(card, this)

  /** Gets the combat row corresponding to the siege type.
   *
   * @param area the player area to query
   * @return the siege row within that player area
   */
  override def getRow(area: PlayerArea): CombatRow = area.getSiegeRow
