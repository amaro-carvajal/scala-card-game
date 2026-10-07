package model.card.unitcard.uniteffect

import model.card.unitcard.*
import model.board.*

/** Represents the "Bond" effect.
 *
 * When activated, this effect multiplies the strength of all cards
 * with the same name in the same combat row by the total number of such cards.
 */

class Bond extends UnitEffect:
  /** Applies the Bond effect to all cards with the same name in the same row.
   *
   * @param board the game board where the effect is applied
   * @param card  the unit card with the Bond effect
   */
  override def applyEffect(board: Board, card: UnitCard): Unit =
    val area =
      if board.getPlayer1Area.contains(card) then board.getPlayer1Area
      else board.getPlayer2Area

    val row = area.getRow(card.getUnitType)

    val sameNameCards = row.getCards.filter(_.getName == card.getName)
    val count = sameNameCards.size

    if count > 1 then
      sameNameCards.foreach { card =>
        val newStrength = card.getStrength * count
        card.setStrength(newStrength)
      }
