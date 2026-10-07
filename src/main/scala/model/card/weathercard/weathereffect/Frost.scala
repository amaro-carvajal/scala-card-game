package model.card.weathercard.weathereffect

import model.board.Board

/** Represents the 'Biting Frost' weather effect.
 * 
 * Sets the strength value of all Melee cards to 1. 
 */
class Frost extends WeatherEffect:
  /** Applies the frost effect to the game board.
   *
   * This method delegates the effect’s execution to the board.
   *
   * @param board the game board on which the effect is applied
   */
  override def applyEffect(board: Board): Unit =
    board.applyFrostEffect()
