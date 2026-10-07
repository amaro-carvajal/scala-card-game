package model.card.weathercard.weathereffect

import model.board.*

/** Represents the 'Impenetrable Fog' weather effect.
 * 
 * Sets the strength value of all Ranged cards to 1.
 */
class Fog extends WeatherEffect:
  /** Applies the fog effect to the game board.
   *
   * This method delegates the effect’s execution to the board.
   *
   * @param board the game board on which the effect is applied
   */
  override def applyEffect(board: Board): Unit =
    board.applyFogEffect()