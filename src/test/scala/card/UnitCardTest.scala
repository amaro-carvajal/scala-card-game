package card

import model.board.{Board, PlayerArea}
import munit.FunSuite
import model.card.*
import model.card.unitcard.*
import model.card.unitcard.unittype.*
import model.card.unitcard.uniteffect.*

class UnitCardTest extends FunSuite{
  var cardMelee: BasicUnitCard = _
  var cardRanged: BasicUnitCard = _
  var cardSiege: BasicUnitCard = _

  var board: Board = _
  var player_area: PlayerArea = _

  override def beforeEach(context: BeforeEach):Unit= {
    cardMelee = new BasicUnitCard("Melee", 2, new Melee)
    cardRanged = new BasicUnitCard("Ranged", 3, new Ranged)
    cardSiege = new BasicUnitCard("Siege", 4, new Siege)

    board = new Board()
    player_area = new PlayerArea()
  }

  test("01 getName") {
    assertEquals(cardMelee.getName, "Melee")
    assertEquals(cardRanged.getName, "Ranged")
    assertEquals(cardSiege.getName, "Siege")
  }

  test("02 equals") {
    assertNotEquals(cardMelee, cardRanged)

    val cardMeleeCpy: BasicUnitCard = new BasicUnitCard("Melee", 2 ,new Melee)
    val cardMeleeName: BasicUnitCard = new BasicUnitCard("Meleee", 2, new Melee)
    val cardMeleeStrength: BasicUnitCard = new BasicUnitCard("Melee", 100, new Melee)
    val cardMeleeMoral: BasicUnitCard = new BasicUnitCard("Melee", 2, new Melee, Some(new Moral))
    val cardMeleeBond: BasicUnitCard = new BasicUnitCard("Melee", 2, new Melee, Some(new Bond))

    assertEquals(cardMelee, cardMeleeCpy)
    assertNotEquals(cardMelee, cardMeleeName)
    assertNotEquals(cardMelee, cardMeleeStrength)
    assertNotEquals(cardMelee, cardMeleeMoral)
    assertNotEquals(cardMelee, cardMeleeBond)
  }

  test("03 compare") {
    assert(cardMelee.compare(cardRanged) < 0)   // "Melee" < "Ranged"
    assert(cardRanged.compare(cardMelee) > 0)   // "Ranged" > "Melee"

    val cardMeleeCpy = new BasicUnitCard("Melee", 2, new Melee)
    assertEquals(cardMelee.compare(cardMeleeCpy), 0)
  }

  test("04 playOnBoard") {
    cardSiege.playOnBoard(board, player_area)
    assertEquals(cardSiege.wasPlayed, true)
    assertEquals(cardRanged.wasPlayed,false)
  }

  test("05 getUnitType, getEffect, getStrength, setStrength") {
    assert(cardMelee.getUnitType.isInstanceOf[Melee])
    assert(cardRanged.getUnitType.isInstanceOf[Ranged])
    assert(cardSiege.getUnitType.isInstanceOf[Siege])

    assertEquals(cardMelee.getStrength, 2)
    cardMelee.setStrength(10)
    assertEquals(cardMelee.getStrength, 10)

    assertEquals(cardMelee.getEffect, None)

    val cardMeleeMoral = new BasicUnitCard("MeleeMoral", 3, new Melee, Some(new Moral))
    assert(cardMeleeMoral.getEffect.isDefined)
    assert(cardMeleeMoral.getEffect.get.isInstanceOf[Moral])
  }

  test("06 getRow and placeOn") {
    val area = new PlayerArea()

    // Melee
    val meleeRow = new Melee().getRow(area)
    assertEquals(meleeRow.getCards.size, 0)
    new Melee().placeOn(area, cardMelee)
    assertEquals(meleeRow.getCards.size, 1)
    assert(meleeRow.getCards.contains(cardMelee))

    // Ranged
    val rangedRow = new Ranged().getRow(area)
    new Ranged().placeOn(area, cardRanged)
    assert(rangedRow.getCards.contains(cardRanged))

    // Siege
    val siegeRow = new Siege().getRow(area)
    new Siege().placeOn(area, cardSiege)
    assert(siegeRow.getCards.contains(cardSiege))
  }

  test("07 Moral effect") {
    val area = board.getPlayer1Area

    val cardMelee1 = new BasicUnitCard("Melee1", 3, new Melee)
    val cardMelee2 = new BasicUnitCard("Melee2", 4, new Melee)
    val cardMeleeMoral = new BasicUnitCard("Melee3", 2, new Melee, Some(new Moral))

    area.getMeleeRow.addCard(cardMelee1)
    area.getMeleeRow.addCard(cardMelee2)
    area.getMeleeRow.addCard(cardMeleeMoral)

    cardMeleeMoral.getEffect.get.applyEffect(board, cardMeleeMoral)

    assertEquals(cardMelee1.getStrength, 4)
    assertEquals(cardMelee2.getStrength, 5)
    assertEquals(cardMeleeMoral.getStrength, 2)
  }

  test("08 Bond effect") {
    val area = board.getPlayer1Area

    val cardRangedBond1 = new BasicUnitCard("RangedBond", 4, new Ranged, Some(new Bond))
    val cardRangedBond2 = new BasicUnitCard("RangedBond", 4, new Ranged, Some(new Bond))
    val cardRangedBond3 = new BasicUnitCard("RangedBond", 4, new Ranged, Some(new Bond))

    area.getRangedRow.addCard(cardRangedBond1)
    area.getRangedRow.addCard(cardRangedBond2)
    area.getRangedRow.addCard(cardRangedBond3)

    cardRangedBond1.getEffect.get.applyEffect(board, cardRangedBond1)

    assertEquals(cardRangedBond1.getStrength, 12)
    assertEquals(cardRangedBond2.getStrength, 12)
    assertEquals(cardRangedBond3.getStrength, 12)
  }
}
