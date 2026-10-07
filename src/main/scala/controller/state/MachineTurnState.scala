package controller.state

import controller.*
import model.player.Player
import model.card.*
import scala.util.Random
import model.card.weathercard.WeatherCard

class MachineTurnState(override val controller: GameController) extends GameState:
  private def executeMachineLogic(): Unit =
    val machine = controller.machine
    val opponent = controller.player
    playCardIfPossible(machine, opponent)

  private def playCardIfPossible(machine: Player, opponent: Player): Unit =
    val sumOpp: Int = controller.board.getPlayer1TotalStrength
    val sumMach: Int = controller.board.getPlayer2TotalStrength
    if (sumMach + machine.getTotalStrengthOfHand) > sumOpp && machine.getHand.nonEmpty then
      val cardToPlay = Random.shuffle(machine.getHand).head
      controller.playCard(machine, cardToPlay, controller.board)

    else
      playWeatherOrPass(machine, opponent)
      
  private def playWeatherOrPass(machine: Player, opponent: Player): Unit =
    val weatherCards = machine.getHand.filter(_.isInstanceOf[WeatherCard])
    if weatherCards.nonEmpty then
      val cardToPlay = Random.shuffle(weatherCards).head.asInstanceOf[WeatherCard]
      controller.playCard(machine, cardToPlay, controller.board)

    else
      passTurn(machine)

  override def onEnter(): Unit =
    println("Ejecutando Turno de la Máquina...")
    executeMachineLogic()