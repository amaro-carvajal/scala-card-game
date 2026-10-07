package model.board
import model.card.weathercard.*
import model.card.*

/** Represents a weather row on the player's side of the board.
 *
 * The weather row holds at most one active weather card at a time.
 */

class WeatherRow extends Row:
  /** The currently active weather card, if any. */
  private var crrt_card: Option[WeatherCard] = None

  /** Gets the currently weather card of the board.
   *
   * @return the currently active weather card
   */
  def getCard: Option[WeatherCard] = crrt_card

  /** Adds a weather card to the row, only if no card is currently active.
   *
   * If a weather card is already present, this method has no effect.
   *
   * @param card the weather card to place on the row
   */
  def addCard(card: WeatherCard): Unit = {
    if crrt_card == None then
      crrt_card = Some(card)
  }

  /** Clears the active weather card from the row. */
  def clearCard(): Unit =
    crrt_card = None
    
    
  
