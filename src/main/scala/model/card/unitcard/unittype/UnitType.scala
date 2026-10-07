package model.card.unitcard.unittype

import model.board.PlayerArea
import model.card.unitcard.UnitCard
import model.board.*

/** Defines the contract for all unit types in the game.
 *
 * This trait delegates the placement logic and row access
 * to each specific unit type implementation.
 */

trait UnitType:

  /** Places a unit card in the correct combat row in the player area.
   *
   * Each concrete unit type implements this method to decide
   * which row the card should be added to.
   *
   * @param area the player’s area where the card will be placed
   * @param card the unit card being placed
   */
  def placeOn(area: PlayerArea, card: UnitCard): Unit

  /** Gets the combat row associated with this unit type.
   *
   * @param area the player’s area to query
   * @return CombatRow corresponding to this unit type
   */
  def getRow(area: PlayerArea): CombatRow

