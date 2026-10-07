package board

import model.card.unitcard.*
import model.card.unitcard.unittype.*
import model.board.*
import munit.FunSuite

class PlayerAreaTest extends FunSuite {
  var area: PlayerArea = _
  var melee_card: BasicUnitCard = _
  var ranged_card: BasicUnitCard = _
  var siege_card: BasicUnitCard = _

  override def beforeEach(context: BeforeEach): Unit = {
    area = new PlayerArea()
    melee_card = new BasicUnitCard("Melee", 5, new Melee())
    ranged_card = new BasicUnitCard("Ranged", 7, new Ranged())
    siege_card = new BasicUnitCard("Siege", 2, new Siege())
  }

  test("01 placeUnit (Melee) + getters"){
    area.placeUnit(melee_card, new Melee)

    assertEquals(area.getMeleeRow.getCards.size, 1)

    assert(area.getSiegeRow.getCards.isEmpty)
    assert(area.getRangedRow.getCards.isEmpty)
  }

  test("02 placeUnit (Ranged) + getters") {
    area.placeUnit(ranged_card, new Ranged)

    assertEquals(area.getRangedRow.getCards.size, 1)

    assert(area.getSiegeRow.getCards.isEmpty)
    assert(area.getMeleeRow.getCards.isEmpty)
  }

  test("03 placeUnit (Siege) + getters") {
    area.placeUnit(siege_card, new Siege)

    assertEquals(area.getSiegeRow.getCards.size, 1)

    assert(area.getMeleeRow.getCards.isEmpty)
    assert(area.getRangedRow.getCards.isEmpty)
  }

  test("04 applyFogEffect") {
    val melee_card_cpy = new BasicUnitCard("Melee_cpy", 5, new Melee())
    val ranged_card_cpy = new BasicUnitCard("Ranged_cpy", 7, new Ranged())
    val siege_card_cpy = new BasicUnitCard("Siege_cpy", 2, new Siege())

    area.getMeleeRow.addCard(melee_card)
    area.getMeleeRow.addCard(melee_card_cpy)
    area.getRangedRow.addCard(ranged_card)
    area.getRangedRow.addCard(ranged_card_cpy)
    area.getSiegeRow.addCard(siege_card)
    area.getSiegeRow.addCard(siege_card_cpy)

    area.applyFogEffect()

    assertEquals(ranged_card.getStrength,1)
    assertEquals(ranged_card_cpy.getStrength,1)

    assertEquals(melee_card.getStrength, 5)
    assertEquals(siege_card.getStrength, 2)
    assertEquals(melee_card_cpy.getStrength, 5)
    assertEquals(siege_card_cpy.getStrength, 2)
  }

  test("05 applyFrostEffect") {
    val melee_card_cpy = new BasicUnitCard("Melee_cpy", 5, new Melee())
    val ranged_card_cpy = new BasicUnitCard("Ranged_cpy", 7, new Ranged())
    val siege_card_cpy = new BasicUnitCard("Siege_cpy", 2, new Siege())

    area.getMeleeRow.addCard(melee_card)
    area.getMeleeRow.addCard(melee_card_cpy)
    area.getRangedRow.addCard(ranged_card)
    area.getRangedRow.addCard(ranged_card_cpy)
    area.getSiegeRow.addCard(siege_card)
    area.getSiegeRow.addCard(siege_card_cpy)

    area.applyFrostEffect()

    assertEquals(melee_card.getStrength,1)

    assertEquals(ranged_card.getStrength, 7)
    assertEquals(siege_card.getStrength, 2)
    assertEquals(ranged_card_cpy.getStrength, 7)
    assertEquals(siege_card_cpy.getStrength, 2)
  }


  test("06 applyRainEffect") {
    val melee_card_cpy = new BasicUnitCard("Melee_cpy", 5, new Melee())
    val ranged_card_cpy = new BasicUnitCard("Ranged_cpy", 7, new Ranged())
    val siege_card_cpy = new BasicUnitCard("Siege_cpy", 2, new Siege())

    area.getMeleeRow.addCard(melee_card)
    area.getMeleeRow.addCard(melee_card_cpy)
    area.getRangedRow.addCard(ranged_card)
    area.getRangedRow.addCard(ranged_card_cpy)
    area.getSiegeRow.addCard(siege_card)
    area.getSiegeRow.addCard(siege_card_cpy)

    area.applyRainEffect()

    assertEquals(siege_card.getStrength,1)
    assertEquals(siege_card_cpy.getStrength, 1)


    assertEquals(melee_card.getStrength, 5)
    assertEquals(ranged_card.getStrength, 7)
    assertEquals(melee_card_cpy.getStrength, 5)
    assertEquals(ranged_card_cpy.getStrength, 7)
  }

  test("07 getTotalStrength") {
    area.placeUnit(siege_card, new Siege)
    area.placeUnit(melee_card, new Melee)
    area.placeUnit(ranged_card, new Ranged)

    assertEquals(area.getTotalStrength, 14)
  }
}
