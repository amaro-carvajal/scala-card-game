package model.card.weathercard

import model.card.*
import model.card.weathercard.weathereffect.*

/** A class representing a basic weather card.
 *
 * @param name The name of the card
 * @param effect The instance of the WeatherEffect that is triggered.
 *
 * @constructor Creates a new basic weather card with its name and effect.
 */
class BasicWeatherCard(private val name: String, private val effect: WeatherEffect) extends WeatherCard:
  /** Gets the effect of the card.
   *
   * @return the card's effect
   */
  override def getEffect: WeatherEffect = effect

  /** Gets the name of the card.
   *
   * @return the card's name
   */
  override def getName: String = name

  /** Checks equality between this weather card and another object.
   *
   * Two cards are considered equal if they have the same name and effect.
   *
   * @param obj the object to compare with
   * @return `true` if the objects are equal, `false` otherwise
   */
  override def equals(obj: Any): Boolean = obj match
    case that: BasicWeatherCard =>
      this.name == that.name &&
        this.effect.getClass == that.effect.getClass
    case _ => false
