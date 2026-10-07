package board

import munit.FunSuite
import model.board.*
import model.card.unitcard.*
import model.card.unitcard.unittype.*

class CombatRowTest extends FunSuite{
  var row1: CombatRow = _
  var row2: CombatRow = _
  var card1: BasicUnitCard = _
  var card2: BasicUnitCard = _
  override def beforeEach(context: BeforeEach):Unit= {
    row1 = new CombatRow()
    row2 = new CombatRow()
    card1 = new BasicUnitCard("Melee", 7, new Melee())
    card2 = new BasicUnitCard("Siege", 4, new Siege())
  }

  test("01 addCard and getCards Test") {
    assert(row1.getCards.isEmpty) //Initial state of a row test.
    assertEquals(row1.getCards.size, 0) //Initial state of a row test.

    row1.addCard(card1) //Add card1 to row1.

    val expected1: Seq[UnitCard] = Seq(card1) //The expected value is a Seq(card1)
    assertEquals(row1.getCards, expected1)
    assertEquals(row1.getCards.size, 1)

    row1.addCard(card2) //Add card2 to row1

    val expected2: Seq[UnitCard] = Seq(card1, card2) //The expected value is a Seq(card1, card2) in that order.
    assertEquals(row1.getCards, expected2)
    assertEquals(row1.getCards.size, 2)
  }
  
  test("02 setToOne") {
    row1.addCard(card1)
    row1.addCard(card2)
    
    assertEquals(card1.getStrength, 7)
    assertEquals(card2.getStrength, 4)
    
    row1.setToOne()

    assertEquals(card1.getStrength, 1)
    assertEquals(card2.getStrength, 1)
    
    assertEquals(row1.getCards.size, 2)
  }

  test("03 getTotalStrength") {
    val card3: UnitCard = new BasicUnitCard("Ranged", 4, new Ranged)
    row1.addCard(card1)
    row1.addCard(card2)
    row1.addCard(card3)
    assertEquals(row1.getTotalStrength, 15)
  }
}

