package controller.state

import controller.*
import model.player.Player
import model.card.Card
import model.board.*

class FinalState(override val controller: GameController) extends GameState {
  val playerGems: Int = controller.player.getGems
  val machineGems: Int = controller.machine.getGems
  
  if (playerGems > machineGems) then println(s"Juego Finalizado. Ganador: ${controller.player.getName}")

  else if (playerGems < machineGems) then println(s"Juego Finalizado. Ganador: ${controller.machine.getName}")

  else println(s"Juego Finalizado. Empate")
  
  override def drawCards(player: Player, amount: Int): Unit = throw new IllegalStateException("Error: Partida finalizada.")

  override def playCard(player: Player, card: Card, board: Board): Unit = throw new IllegalStateException("Error: Partida finalizada.")

  override def passTurn(player: Player): Unit = throw new IllegalStateException("Error: Partida finalizada.")
}
