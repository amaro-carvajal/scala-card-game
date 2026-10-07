package model.card.unitcard.uniteffect

import model.card.unitcard.*
import model.board.*

/** Represents the "Moral" effect.
 *
 * When activated, this effect increases the strength of all other cards
 * in the same row by +1, excluding the card that triggered the effect.
 */

class Moral extends UnitEffect:
  /** Applies the Moral effect to all allied cards in the same row.
   *
   * The card that triggered the effect is excluded from the bonus.
   *
   * @param board the game board where the effect is applied
   * @param card  the unit card with the Moral effect
   */
  override def applyEffect(board: Board, card: UnitCard): Unit =
    val area =
      if board.getPlayer1Area.contains(card) then board.getPlayer1Area
      else board.getPlayer2Area

    val row = area.getRow(card.getUnitType)

    for c <- row.getCards do
      if (c ne card) then
        c.setStrength(c.getStrength + 1)
