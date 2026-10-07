package model.card.weathercard

import model.card.*
import model.card.weathercard.weathereffect.*
import model.board.*

/** Defines the fundamental contract for any object that is considered a "WeatherCard"
 * in the game.
 */
trait WeatherCard extends Card:
  /** Indicates whether this weather card has already been played. */
  var wasPlayed: Boolean = false

  /** Gets the effect of the card.
   *
   * @return the card's effect
   */
  def getEffect: WeatherEffect

  /** Plays this weather card onto the board.
   *
   * When played, the card is placed into the shared weather row of the board,
   * its associated effect is immediately applied, and its state is marked as played.
   *
   * @param board the game board on which the weather card is played
   * @param playerArea the area of the player who played this card
   */
  override def playOnBoard(board: Board, playerArea: PlayerArea): Unit =
    board.placeWeatherCard(this)
    this.getEffect.applyEffect(board)
    this.wasPlayed = true