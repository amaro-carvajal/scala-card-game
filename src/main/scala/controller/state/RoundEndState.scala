package controller.state

import controller.*

class RoundEndState(override val controller: GameController) extends GameState:
  private def checkFinalization(): Unit =
    val playerGems = controller.player.getGems
    val machineGems = controller.machine.getGems

    if (playerGems <= 0 || machineGems <= 0) then
      notifyObservers("GAME_OVER")
      controller.state = new FinalState(controller)
      
    else
      controller.roundNumber += 1
      println(s"Comenzando Ronda ${controller.roundNumber}.")
      drawCards(controller.player, 3)
      drawCards(controller.machine, 3)
      if controller.roundNumber%2 == 0 then
        controller.activePlayer = controller.player
        controller.state = new PlayerTurnState(controller)
      else
        controller.activePlayer = controller.machine
        controller.state = new MachineTurnState(controller)

  override def onEnter(): Unit = checkFinalization()

