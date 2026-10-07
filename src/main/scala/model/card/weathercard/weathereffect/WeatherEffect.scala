package model.card.weathercard.weathereffect

import model.board.Board

/** Defines the fundamental contract for all weather effects in the game.
 */

trait WeatherEffect:
  /** Applies the effect to the game board.
   *
   * This method delegates the effect’s execution to the board.
   *
   * @param board the game board on which the effect is applied
   */
  def applyEffect(board: Board): Unit
