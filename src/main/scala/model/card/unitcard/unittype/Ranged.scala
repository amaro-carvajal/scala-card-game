package model.card.unitcard.unittype

import model.board.PlayerArea
import model.card.*
import model.card.unitcard.UnitCard
import model.board.*

/** Represents the Ranged deployment type.
 *
 * Units marked with this type are intended to be played into the ranged combat row.
 */
class Ranged extends UnitType:
  /** Places the given unit card in the ranged row of the player area.
   *
   * @param area the player area where the card will be placed
   * @param card the unit card being placed
   */
  override def placeOn(area: PlayerArea, card: UnitCard): Unit =
    area.placeUnit(card, this)

  /** Gets the combat row corresponding to the ranged type.
   *
   * @param area the player area to query
   * @return the ranged row within that player area
   */
  override def getRow(area: PlayerArea): CombatRow = area.getRangedRow