package model.card.unitcard
import model.card.*
import model.card.unitcard.uniteffect.UnitEffect
import model.card.unitcard.unittype.UnitType

/** A class representing a basic unit card.
 *
 * @param name The name of the card.
 * @param initialStrength The initial strength of the card.
 * @param unitType The UnitType associated with the card.
 * @param effect An Option containing the UnitEffect if present, or None otherwise.
 *
 * @constructor Creates a new basic unit card with its name, strength, unit type and effect (if any).
 */

class BasicUnitCard(private val name: String, private val initialStrength: Int, private val unitType: UnitType, private val effect: Option[UnitEffect] = None) extends UnitCard:
  /** Internal mutable strength field tracking the card's current strength. */
  private var _strength: Int = initialStrength

  /** Auxiliary constructor for creating a basic unit card without a special effect.
   * Defaults the 'effect' field to None.
   */
  def this(name: String, initialStrength: Int, unitType: UnitType) =
    this(name, initialStrength, unitType, None)

  /** Gets the name of the card.
   *
   * @return the card's name
   */
  override def getName: String = name

  /** Gets the strength of the card.
   *
   * @return the card's strength
   */
  override def getStrength: Int = _strength

  /** Gets the unit type of the card.
   *
   * @return the card's unit type
   */
  override def getUnitType: UnitType = unitType

  /** Gets the effect of the card.
   *
   * @return the card's effect
   */
  override def getEffect: Option[UnitEffect] = effect

  /** Sets the strength value of the card.
   *
   * @param newStrength the new strength value to assign to the card
   */
  def setStrength(newStrength: Int): Unit =
    _strength = newStrength

  /** Checks equality between this unit card and another object.
   *
   * Two cards are considered equal if they have the same name, strength, unit type and effect.
   *
   * @param obj the object to compare with
   * @return `true` if the objects are equal, `false` otherwise
   */
  override def equals(obj: Any): Boolean = obj match
    case that: BasicUnitCard =>
      this.name == that.name &&
        this.getStrength == that.getStrength &&
        this.unitType.getClass == that.unitType.getClass &&
        this.effect == that.effect
    case _ => false
