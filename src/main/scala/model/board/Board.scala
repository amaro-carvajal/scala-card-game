package model.board

import model.card.weathercard.*

/** Represents the game board.
 *
 * The board contains two player areas and a single shared weather row.
 * It manages the placement of weather cards and applies weather effects
 * to both player areas simultaneously.
 */

class Board:
  /** Player 1 area of the board. */
  private val player1Area = new PlayerArea()

  /** Player 2 area of the board. */
  private val player2Area = new PlayerArea()

  /** The shared weather row that holds the active weather card. */
  private val weather_row: WeatherRow = new WeatherRow()

  /** Returns Player 1’s area. */
  def getPlayer1Area: PlayerArea = player1Area

  /** Returns Player 1’s area. */
  def getPlayer2Area: PlayerArea = player2Area

  /** Returns the weather row. */
  def getWeatherRow: WeatherRow = weather_row

  /** Places a weather card on the board. */
  def placeWeatherCard(card: WeatherCard): Unit =
    weather_row.addCard(card)

  /** Removes all weather cards from the weather row. */
  def clearWeather(): Unit =
    weather_row.clearCard()

  /** Applies the fog effect to both player areas. */
  def applyFogEffect(): Unit =
    player1Area.applyFogEffect()
    player2Area.applyFogEffect()

  /** Applies the frost effect to both player areas. */
  def applyFrostEffect(): Unit =
    player1Area.applyFrostEffect()
    player2Area.applyFrostEffect()

  /** Applies the rain effect to both player areas. */
  def applyRainEffect(): Unit =
    player1Area.applyRainEffect()
    player2Area.applyRainEffect()

  def getPlayer1TotalStrength: Int =
    player1Area.getTotalStrength

  def getPlayer2TotalStrength: Int =
    player2Area.getTotalStrength