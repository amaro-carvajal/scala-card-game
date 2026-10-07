package controller.state

import model.player.Player
import model.card.Card
import model.board.*
import controller.*
import observer.*


trait GameState extends BaseSubject[String]:
  val controller: GameController
  attach(controller)
  
  def onEnter(): Unit = throw new IllegalStateException("Error: No puedes usar onEnter() en este estado")
  
  def drawCards(player: Player, amount: Int): Unit =
    (1 to amount).foreach(_ => player.drawCard())
    println(s"${player.getName} robó $amount cartas. Mano: ${player.getHand.size}")
    
  def playCard(player: Player, card: Card, board: Board): Unit =
    if player != controller.activePlayer then
      return println(f"Error: No es tu turno. El jugador activo es ${controller.activePlayer.getName}.")


    if player.hasPassed then
      return println(s"Error: ${player.getName} ya pasó su turno, no puede jugar más cartas.")

    player.playACard(card, board)
    println(s"${player.getName} jugó la carta ${card.getName}.")
    controller.changeTurn()
    
  def passTurn(player: Player): Unit =
    if player != controller.activePlayer then
      return println(f"Error: No es tu turno. El jugador activo es ${controller.activePlayer.getName}.")
      
    player.setPassed(true)
    println(s"${player.getName} ha pasado su turno.")
    controller.changeTurn()

