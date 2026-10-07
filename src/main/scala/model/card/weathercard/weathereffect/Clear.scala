package model.card.weathercard.weathereffect

import model.board.Board

/** Represents the 'Clear Weather' effect.
 *
 * This effect is unique in that it removes all existing negative weather effects 
 * (like Fog, Frost, or Rain) from the game board, resetting the affected rows to their 
 * normal strength values.
 */
class Clear extends WeatherEffect:
  /** Applies the clear effect to the game board.
   *
   * This method delegates the effect’s execution to the board.
   *
   * @param board the game board on which the effect is applied
   */
  override def applyEffect(board: Board): Unit =
    board.clearWeather()
