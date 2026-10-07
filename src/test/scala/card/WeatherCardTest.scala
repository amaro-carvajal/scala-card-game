package card

import model.board.{Board, PlayerArea}
import munit.FunSuite
import model.card.*
import model.card.weathercard.*
import model.card.weathercard.weathereffect.*

class WeatherCardTest extends FunSuite {
  var cardFrost: BasicWeatherCard = _
  var cardFog: BasicWeatherCard = _
  var cardRain: BasicWeatherCard = _
  var cardClear: BasicWeatherCard = _

  var board: Board = _
  var player_area: PlayerArea = _

  override def beforeEach(context: BeforeEach): Unit = {
    cardFrost = new BasicWeatherCard("Frost", new Frost)
    cardFog = new BasicWeatherCard("Fog", new Fog)
    cardRain = new BasicWeatherCard("Rain", new Rain)
    cardClear = new BasicWeatherCard("Clear", new Clear)

    board = new Board()
    player_area = new PlayerArea()
  }

  test("01 getName") {
    assertEquals(cardFrost.getName, "Frost")
    assertEquals(cardFog.getName, "Fog")
    assertEquals(cardRain.getName, "Rain")
    assertNotEquals(cardClear.getName, "Cleear")

  }

  test("02 equals") {
    val cardFrostCpy: BasicWeatherCard = BasicWeatherCard("Frost", new Frost)
    val cardFrostNotEqual: BasicWeatherCard = BasicWeatherCard("Frost", new Rain)

    assertEquals(cardFrostCpy, cardFrost)
    assertNotEquals(cardFrost, cardRain)
    assertNotEquals(cardFrost, cardFrostNotEqual)
  }

  test("03 compare") {
    assertNotEquals(cardFrost.compare(cardClear), 0)

    assert(cardFrost.compare(cardClear) > 0) //(Frost > Clear)

    assert(cardClear.compare(cardFrost) < 0) //(Clear < Frost)

    val cardFrostCpy = BasicWeatherCard("Frost", new Frost)
    assertEquals(cardFrost.compare(cardFrostCpy), 0)
  }

  test("04 playOnBoard") {
    cardFrost.playOnBoard(board, player_area)
    assertEquals(cardFrost.wasPlayed, true)
    assertEquals(cardFog.wasPlayed,false)
  }

  test("05 getEffect") {
    assert(cardFrost.getEffect.isInstanceOf[Frost])
    assert(!cardRain.getEffect.isInstanceOf[Frost])
  }
  // The effect testing was implemented in the BoardTest
}