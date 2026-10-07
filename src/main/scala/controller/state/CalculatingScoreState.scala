package controller.state

import controller.*

class CalculatingScoreState(override val controller: GameController) extends GameState:
  private def calculateScore(): Unit =
    println("Fase de Cálculo de Puntaje: Determinando ganador de la ronda.")

    val player = controller.player
    val machine = controller.machine
    
    val p1Power = player.getArea.getTotalStrength
    val p2Power = machine.getArea.getTotalStrength

    println(s"${player.getName} Power: $p1Power | ${machine.getName} Power: $p2Power")

    if (p1Power > p2Power) then
      machine.loseGem()
      println(s"${player.getName} gana la ronda. ${machine.getName} pierde gema. Gemas restantes: ${machine.getGems}")
    
    else if (p2Power > p1Power) then
      player.loseGem()
      println(s"${machine.getName} gana la ronda. ${player.getName} pierde gema. Gemas restantes: ${player.getGems}")

    else
      player.loseGem()
      machine.loseGem()
      println(s"Empate. Ambos jugadores pierden 1 gema. Gemas ${player.getName}: ${player.getGems} | Gemas ${machine.getName}: ${machine.getGems}")
    
    controller.machine.resetPassed()
    controller.player.resetPassed()
    controller.state = new RoundEndState(controller)
    controller.state.onEnter()

  override def onEnter(): Unit = calculateScore()