package player

import model.board.*
import model.card.unitcard.*
import model.card.unitcard.unittype.*
import model.card.weathercard.*
import model.card.weathercard.weathereffect.*
import model.card.*
import model.player.*
import munit.FunSuite
import scala.collection.mutable.ArrayBuffer

class PlayerTest extends FunSuite{
    var player: Player = _
    var area: PlayerArea = _

    var deck: Deck = _
    var hand: Hand = _

    var board: Board = _
    var cardA: BasicUnitCard = _
    var cardB: BasicWeatherCard = _

    override def beforeEach(context: BeforeEach):Unit = {
      area = new PlayerArea()
      deck = new Deck(ArrayBuffer.empty[Card])
      hand = new Hand()
      board = new Board()

      player = new Player("TestPlayer", area, deck, hand)

      cardA = new BasicUnitCard("UnitA", 4, new Melee)
      cardB = new BasicWeatherCard("WeatherB", new Rain)

      hand.setCards(Seq(cardA))
      deck.setCards(Seq(cardB))
    }

    test("01 getName and getGems") {
      assertEquals(player.getName, "TestPlayer")
      assertEquals(player.getGems, 2)
    }

    test("02 loseGem") {
      player.loseGem()
      assertEquals(player.getGems, 1)
    }

    test("03 getHand and setHand") {
      assertEquals(player.getHand, Seq(cardA))

      player.setHand(Seq(cardB))

      assertEquals(player.getHand, Seq(cardB))
    }

    test("04 getDeck and setDeck") {
      val cardC = cardA
      val cardD = cardB

      assertEquals(player.getDeck, Seq(cardB))

      val newDeckContent = Seq(cardC, cardD)
      player.setDeck(newDeckContent)

      assertEquals(player.getDeck, newDeckContent)

      assertEquals(deck.getCards, newDeckContent)
    }

    test("05 drawCard") {
      assertEquals(player.getHand, Seq(cardA))

      player.drawCard()

      assertEquals(player.getHand, Seq(cardA, cardB))

      assert(deck.getCards.isEmpty)
    }

    test("06 playACard (UnitCard)") {
      assert(cardA.wasPlayed == false)

      player.playACard(cardA, board)

      assert(player.getHand.isEmpty)

      assert(cardA.wasPlayed == true)
    }

    test("07 playACard (WeatherCard)") {
      player.setHand(Seq(cardB))
      assert(cardB.wasPlayed == false)

      player.playACard(cardB, board)

      assert(player.getHand.isEmpty)

      assert(cardB.wasPlayed == true)

      assertEquals(board.getWeatherRow.getCard, Some(cardB))
    }

    test("08 getTotalStrength") {
      val cardC = cardA
      hand.setCards(Seq(cardA, cardB, cardC))
      assertEquals(hand.getTotalStrength, 8)
    }
  }