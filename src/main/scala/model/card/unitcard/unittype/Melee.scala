package model.card.unitcard.unittype

import model.board.PlayerArea
import model.card.*
import model.card.unitcard.UnitCard
import model.board.*

/** Represents the Melee deployment type. */

class Melee extends UnitType:
  /** Places the given unit card in the melee row of the player area.
   *
   * @param area the player area where the card will be placed
   * @param card the unit card being placed
   */
  override def placeOn(area: PlayerArea, card: UnitCard): Unit =
    area.placeUnit(card, this)

  /** Gets the combat row corresponding to the melee type.
   *
   * @param area the player area to query
   * @return the melee row within that player area
   */
  override def getRow(area: PlayerArea): CombatRow = area.getMeleeRow

