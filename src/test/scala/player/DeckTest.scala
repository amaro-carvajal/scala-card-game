package player

import munit.FunSuite
import model.card.unitcard.*
import model.card.unitcard.unittype.*
import model.player.*
import scala.collection.mutable.ArrayBuffer
import model.card.*

class DeckTest extends FunSuite {
  var deck: Deck = _
  var cardR1: BasicUnitCard = _
  var cardR2: BasicUnitCard = _
  var cardS1: BasicUnitCard = _
  var cardS2: BasicUnitCard = _
  var cardM1: BasicUnitCard = _
  var cardM2: BasicUnitCard = _

  override def beforeEach(context: BeforeEach):Unit = {
    cardR1 = new BasicUnitCard("R1", 1, new Ranged)
    cardR2 = new BasicUnitCard("R2", 1, new Ranged)
    cardS1 = new BasicUnitCard("S1", 1, new Siege)
    cardS2 = new BasicUnitCard("S2", 1, new Siege)
    cardM1 = new BasicUnitCard("M1", 1, new Melee)
    cardM2 = new BasicUnitCard("M2", 1, new Melee)

    deck = new Deck(ArrayBuffer.empty[Card])
  }

  test ("01 setCards and getCards") {
    assert(deck.getCards.isEmpty)
    deck.setCards(Seq(cardR1, cardR2, cardS1, cardS2))
    assertEquals(deck.getCards, Seq(cardR1, cardR2, cardS1, cardS2))
  }

  test ("02 shuffle") {
    deck.setCards(Seq(cardR1, cardR2, cardS1, cardS2, cardM1, cardM2))
    deck.shuffle()
    val cardsToChek: Seq[Card] = Seq(cardR1, cardR2, cardS1, cardS2, cardM1, cardM2)
    assert(cardsToChek.forall(card => deck.getCards.contains(card)))

    val numRuns = 1000
    val originalOrder = cardsToChek
    var timesChanged = 0

    (1 to numRuns).foreach { _ =>
      val deckN: Deck = new Deck(ArrayBuffer.empty[Card])
      deckN.setCards(Seq(cardR1, cardR2, cardS1, cardS2, cardM1, cardM2))
      deckN.shuffle()

      val shuffledOrder = deckN.getCards
      if (shuffledOrder != originalOrder)
        timesChanged += 1
    }

    assert(timesChanged > 0)
  }
}
