package controller

import controller.state.*
import model.card.*
import model.card.unitcard.*
import model.card.unitcard.unittype.*
import munit.FunSuite

class StateTest extends FunSuite{
  var controller: GameController = _

  override def beforeEach(context: BeforeEach): Unit = {
    controller = new GameController
  }

  test("01 InitializingState all methods") {
    val initial: InitializingState = new InitializingState(controller)
    initial.onEnter()

    val player = controller.player
    val card = player.getHand.head
    val board = controller.board

    assertEquals(controller.player.getHand.size, 10)
    assertEquals(controller.machine.getHand.size, 10)

    intercept[IllegalStateException] {
      initial.playCard(player, card, board)
    }

    intercept[IllegalStateException] {
      initial.passTurn(player)
    }
  }

  test("02 PlayerTurnState playCard") {
    controller.start()
    val player = controller.player
    val card = player.getHand.head
    val board = controller.board

    assertEquals(player.getHand.size, 10)
    controller.playCard(player, card, board)
    assertEquals(player.getHand.size, 9)
  }

  test("03 PlayerTurnState drawCard") {
    controller.start()
    val player = controller.player

    assertEquals(player.getHand.size, 10)
    controller.state.drawCards(player, 4)
    assertEquals(player.getHand.size, 14)
  }

  test("04 PlayerTurnState passTurn") {
    controller.start()
    val player = controller.player

    assertEquals(player.getHand.size, 10)
    controller.passTurn(player)
    assertEquals(player.getHand.size, 13)// The player passes the turn, so the round ends (after the machine plays),
    // and at the start of the new round player adds 3 cards to his hand.
  }

  test("05 PlayerTurnState onEnter") {
    controller.start()
    val playerTurn = controller.state
    intercept[IllegalStateException] {
      playerTurn.onEnter()
    }
  }

  test("06 MachineTurnState") {
    controller.start()
    controller.passTurn(controller.player)

    assertEquals(controller.machine.getHand.size, 3)// The player passes the turn, so the machine plays (plays all its cards),
    // and at the start of the new round machine adds 3 cards to his hand. This proves that the machine logic worked effectively (test all methods).
  }

  test("07 CalculatingScoreState machine wins") {
    controller.start()
    controller.passTurn(controller.player)

    assertEquals(controller.player.getGems, 1)
    assertEquals(controller.machine.getGems, 2)
  }

  test("07 CalculatingScoreState player wins") {
    controller.start()
    val card = new BasicUnitCard("GOOD", 10000, new Melee)
    val card2 = new BasicUnitCard("BAD", 1, new Melee)
    controller.player.setHand(Seq(card))
    controller.machine.setHand(Seq(card2))
    controller.playCard(controller.player, card, controller.board)
    controller.passTurn(controller.player)

    assertEquals(controller.machine.getGems, 1)
    assertEquals(controller.player.getGems, 2)
  }

  test("08 CalculatingScoreState tie") {
    controller.start()
    val card = new BasicUnitCard("Zero", 0, new Melee)
    controller.player.setHand(Seq(card))
    controller.machine.setHand(Seq(card))
    controller.playCard(controller.player, card, controller.board)
    controller.passTurn(controller.player)
    assertEquals(controller.player.getGems, 1)
    assertEquals(controller.machine.getGems, 1)
  }

  test("09 FinalState player win") {
    controller.start()
    val card = new BasicUnitCard("GOOD", 10000, new Melee)
    val card2 = new BasicUnitCard("BAD", 1, new Melee)
    controller.player.setHand(Seq(card))
    controller.machine.setHand(Seq(card2))
    controller.playCard(controller.player, card, controller.board)
    controller.passTurn(controller.player)
    controller.player.setHand(Seq(card))
    controller.machine.setHand(Seq(card2))
    controller.playCard(controller.player, card, controller.board)
    controller.passTurn(controller.player)
    assert(controller.state.isInstanceOf[FinalState])
    assertEquals(controller.player.getGems, 2)
    assertEquals(controller.machine.getGems, 0)
  }

  test("10 FinalState machine win") {
    controller.start()
    val card = new BasicUnitCard("GOOD", 10000, new Melee)
    val card2 = new BasicUnitCard("BAD", 1, new Melee)
    controller.player.setHand(Seq(card2))
    controller.machine.setHand(Seq(card))
    controller.playCard(controller.player, card2, controller.board)
    controller.passTurn(controller.player)
    controller.player.setHand(Seq(card2))
    controller.machine.setHand(Seq(card))
    controller.playCard(controller.player, card2, controller.board)
    controller.passTurn(controller.player)
    assert(controller.state.isInstanceOf[FinalState])
    assertEquals(controller.player.getGems, 0)
    assertEquals(controller.machine.getGems, 2)
  }

  test("11 FinalState tie") {
    controller.start()
    val card = new BasicUnitCard("Zero", 0, new Melee)
    controller.player.setHand(Seq(card))
    controller.machine.setHand(Seq(card))
    controller.playCard(controller.player, card, controller.board)
    controller.passTurn(controller.player)
    controller.player.setHand(Seq(card))
    controller.machine.setHand(Seq(card))
    controller.playCard(controller.player, card, controller.board)
    controller.passTurn(controller.player)
    assert(controller.state.isInstanceOf[FinalState])
    assertEquals(controller.player.getGems, 0)
    assertEquals(controller.machine.getGems, 0)
  }

  test("12 FinalState illegal methods") {
    controller.start()
    val card = new BasicUnitCard("Zero", 0, new Melee)
    controller.player.setHand(Seq(card))
    controller.machine.setHand(Seq(card))
    controller.playCard(controller.player, card, controller.board)
    controller.passTurn(controller.player)
    controller.player.setHand(Seq(card))
    controller.machine.setHand(Seq(card))
    controller.playCard(controller.player, card, controller.board)
    controller.passTurn(controller.player)
    assert(controller.state.isInstanceOf[FinalState])

    intercept[IllegalStateException] {
      controller.state.onEnter()
    }

    intercept[IllegalStateException] {
      controller.state.playCard(controller.player, card, controller.board)
    }

    intercept[IllegalStateException] {
      controller.state.drawCards(controller.player, 3)
    }

    intercept[IllegalStateException] {
      controller.state.passTurn(controller.player)
    }
  }
}
