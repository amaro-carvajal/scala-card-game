package model.card.weathercard.weathereffect

import model.board.*

/** Represents the 'Torrential Rain' weather effect.
 *
 * Sets the strength value of all Siege cards to 1.
 */

class Rain extends WeatherEffect:
  /** Applies the rain effect to the game board.
   *
   * This method delegates the effect’s execution to the board.
   *
   * @param board the game board on which the effect is applied
   */
  override def applyEffect(board: Board): Unit =
    board.applyRainEffect()

