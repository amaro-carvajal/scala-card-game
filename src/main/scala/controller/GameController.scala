package controller

import observer.*
import model.player.*
import model.board.*
import model.card.*
import controller.state.*


class GameController extends Observer[String]:
  var board: Board = new Board()
  var player: Player = _
  var machine: Player = _

  var activePlayer: Player = player
  var roundNumber: Int = 0

  var state: GameState = new InitializingState(this)

  override def update(sub: Subject[String], response: String): Unit =
    if response =="GAME_OVER" then
        println("*** NOTIFICACIÓN RECIBIDA POR EL CONTROLADOR ***")
        println("El GameController ha recibido la señal de 'GAME_OVER'")
        
        
  def start(): Unit = state.onEnter()
  
  def playCard(player: Player, card: Card, board: Board): Unit = state.playCard(player, card, board)

  def passTurn(player: Player): Unit = state.passTurn(player)

  def changeTurn(): Unit =
    val opponent = if (activePlayer == player) machine else player

    if (opponent.hasPassed && activePlayer.hasPassed) then
      println("Ambos jugadores pasaron. Se termina la ronda")
      state = new CalculatingScoreState(this)
      state.onEnter()
      return

    if (!opponent.hasPassed) then
      activePlayer = opponent
      println(s"Turno alternado a: ${activePlayer.getName}.")

    else
      println(s"El oponente ${opponent.getName} pasó. El turno permanece en: ${activePlayer.getName}.")

    if (activePlayer == machine) then
      state = new MachineTurnState(this)
      state.onEnter()

    else
      state = new PlayerTurnState(this)