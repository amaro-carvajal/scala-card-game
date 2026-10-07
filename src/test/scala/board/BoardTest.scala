package board

import munit.FunSuite
import model.board.*
import model.card.unitcard.*
import model.card.unitcard.unittype.*
import model.card.weathercard.*
import model.card.weathercard.weathereffect.*


class BoardTest extends FunSuite{
  var board: Board = _

  var p1_ranged: BasicUnitCard = _
  var p1_melee: BasicUnitCard = _
  var p1_siege: BasicUnitCard = _

  var p2_ranged: BasicUnitCard = _
  var p2_melee: BasicUnitCard = _
  var p2_siege: BasicUnitCard = _

  var fog_card: BasicWeatherCard = _
  var frost_card: BasicWeatherCard = _

  override def beforeEach(context: BeforeEach): Unit = {
    board = new Board()

    p1_ranged = new BasicUnitCard("P1 Ranged", 7, new Ranged())
    p1_melee = new BasicUnitCard("P1 Melee", 8, new Melee())
    p1_siege = new BasicUnitCard("P1 Siege", 4, new Siege())

    p2_ranged = new BasicUnitCard("P2 Ranged", 6, new Ranged())
    p2_melee = new BasicUnitCard("P2 Melee", 9, new Melee())
    p2_siege = new BasicUnitCard("P2 Siege", 3, new Siege())

    fog_card = new BasicWeatherCard("Fog", new Fog())
    frost_card = new BasicWeatherCard("Frost", new Frost())

    board.getPlayer1Area.getRangedRow.addCard(p1_ranged)
    board.getPlayer1Area.getMeleeRow.addCard(p1_melee)
    board.getPlayer1Area.getSiegeRow.addCard(p1_siege)

    board.getPlayer2Area.getRangedRow.addCard(p2_ranged)
    board.getPlayer2Area.getMeleeRow.addCard(p2_melee)
    board.getPlayer2Area.getSiegeRow.addCard(p2_siege)

  }

  test("01 getPlayer1Area and getPlayer2Area") {
    val area1 = board.getPlayer1Area
    val area2 = board.getPlayer2Area

    assert(area1 != null)
    assert(area2 != null)

    assertNotEquals(area1, area2)
  }

  test("02 getWeatherRow and placeWeatherCard") {
    board.placeWeatherCard(fog_card)

    assertEquals(board.getWeatherRow.getCard, Some(fog_card))
    assert(board.getWeatherRow.getCard.isDefined)
  }

  test("03 clearWeather") {
    board.placeWeatherCard(frost_card)
    assert(board.getWeatherRow.getCard.isDefined)

    board.clearWeather()
    assert(board.getWeatherRow.getCard.isEmpty)
  }

  test("04 applyFogEffect") {
    assertEquals(p1_ranged.getStrength, 7)
    assertEquals(p2_ranged.getStrength, 6)

    board.applyFogEffect()

    assertEquals(p1_ranged.getStrength, 1)
    assertEquals(p2_ranged.getStrength, 1)

    assertEquals(p1_melee.getStrength, 8)
    assertEquals(p2_melee.getStrength, 9)
    assertEquals(p1_siege.getStrength, 4)
    assertEquals(p2_siege.getStrength, 3)
  }

  test("05 applyFrostEffect") {
    assertEquals(p1_melee.getStrength, 8)
    assertEquals(p2_melee.getStrength, 9)

    board.applyFrostEffect()

    assertEquals(p1_melee.getStrength, 1)
    assertEquals(p2_melee.getStrength, 1)

    assertEquals(p1_ranged.getStrength, 7)
    assertEquals(p2_ranged.getStrength, 6)
    assertEquals(p1_siege.getStrength, 4)
    assertEquals(p2_siege.getStrength, 3)
  }

  test("06 applyRainEffect") {
    assertEquals(p1_siege.getStrength, 4)
    assertEquals(p2_siege.getStrength, 3)

    board.applyRainEffect()

    assertEquals(p1_siege.getStrength, 1)
    assertEquals(p2_siege.getStrength, 1)

    assertEquals(p1_ranged.getStrength, 7)
    assertEquals(p2_ranged.getStrength, 6)
    assertEquals(p1_melee.getStrength, 8)
    assertEquals(p2_melee.getStrength, 9)
  }

  test("07 getPlayer1TotalStrength and getPlayer2TotalStrength") {
    assertEquals(board.getPlayer1TotalStrength, 19)
    assertEquals(board.getPlayer2TotalStrength, 18)
  }
}
