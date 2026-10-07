package model.card.unitcard.uniteffect

import model.board.Board
import model.card.unitcard.UnitCard

/** Defines the contract for all unit effects.
 *
 * A UnitEffect represents a special ability that a unit card
 * can trigger when played on the board (e.g., Moral, Bond).
 * Each effect modifies the state of the board or other cards in a specific way.
 */

trait UnitEffect:
  /** Applies the effect of this ability on the game board.
   *
   * Implementations define how the effect interacts with other cards or rows.
   *
   * @param board the game board where the effect is applied
   * @param card the unit card that triggered this effect
   */
  def applyEffect(board: Board, card: UnitCard) : Unit
