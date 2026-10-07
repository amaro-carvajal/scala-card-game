package controller

import munit.FunSuite
import controller.state.*
import model.card.*

class GameControllerTest extends FunSuite {
  var controller: GameController = _

  override def beforeEach(context: BeforeEach): Unit = {
    controller = new GameController
  }
  test("01 initial state and round number") {
    assert(controller.state.isInstanceOf[InitializingState])
    assert(controller.roundNumber==0)
  }

  test("02 start InitializingState") {
    controller.start()
    assert(controller.state.isInstanceOf[PlayerTurnState])
    assertEquals(controller.player.getHand.size, 10)
    assertEquals(controller.machine.getHand.size, 10)
  }

  test("03 PlayerTurnState (playCard) and MachineTurnState") {
    controller.start()
    val card: Card = controller.player.getHand.head
    controller.playCard(controller.player, card, controller.board)
    assertEquals(controller.player.getHand.size, 9)
  }

  test("04 EndRoundState and CalculatingScoreState") {
    controller.start()
    val card: Card = controller.player.getHand.head
    controller.playCard(controller.player, card, controller.board)
    controller.passTurn(controller.activePlayer)
    assert(controller.machine.getGems == 1 || controller.player.getGems == 1) // One of the two lost, so he has 1 gem (the player).
    assertEquals(controller.roundNumber, 2) // Round number changed to 2
    assert(!controller.player.hasPassed && !controller.machine.hasPassed) // This proves that the state passed through CalculatingScoreState, as it resets the hasPassed.
    assert(controller.state.isInstanceOf[PlayerTurnState]) //As it is round 2, first the machine plays and then the player plays, so the state changes to PlayerTurnState.
  }

  test("05 FinalState") {
    //Round 1
    controller.start()
    val card: Card = controller.player.getHand.head
    controller.playCard(controller.player, card, controller.board)
    controller.passTurn(controller.activePlayer)

    //Round 2
    val card2: Card = controller.player.getHand.head
    controller.playCard(controller.player, card2, controller.board)
    controller.passTurn(controller.activePlayer)

    assert(controller.state.isInstanceOf[FinalState]) //The player lost the game.
  }
}
