package controller.state

import controller.*
import model.player.*
import model.card.*
import scala.util.Random
import model.card.unitcard.*
import model.card.weathercard.*
import model.card.unitcard.unittype.*
import model.card.weathercard.weathereffect.*
import model.board.*

class InitializingState(override val controller: GameController) extends GameState:
  private def createUnitCard(i: Int, unitType: model.card.unitcard.unittype.UnitType): Card =
    val (indexOffset, namePrefix) = unitType match {
      case _: Siege  => (0, "Siege")
      case _: Melee  => (10, "Melee")
      case _: Ranged => (20, "Ranged")
    }
    
    val idx = i - indexOffset

    val (cardName, strength) = unitType match {

      case _: Siege => idx match {
        case 1 => ("Legendary Siege Card", 8)
        case 2 | 3 => ("Epic Siege Card", 7)
        case 4 | 5 | 6 => ("Special Siege Card", 5)
        case _ => ("Common Siege Card", 3)
      }

      case _: Melee => idx match {
        case 1 | 2 => ("Legendary Melee Card", 6)
        case 3 | 4 | 5 => ("Epic Melee Card", 5)
        case 6 | 7 | 8 | 9 => ("Special Melee Card", 4)
        case 10 => ("Common Melee Card", 3)
      }

      case _: Ranged => idx match {
        case 1  => ("Legendary Ranged Card", 7)
        case 2 | 3 => ("Epic Ranged Card", 5)
        case 4 | 5 | 6 | 7 => ("Special Ranged Card", 4)
        case _ => ("Common Ranged Card", 3)
      }
    }

    new BasicUnitCard(s"$cardName", strength, unitType)
  
  private def generateFullDeck(): Seq[Card] =
    val fullDeck: Seq[Card] = (1 to 50).map { i =>
      i match {
        // Units cards.
        case i if i >= 1 && i <= 10 =>
          createUnitCard(i, new Siege())

        case i if i >= 11 && i <= 20 =>
          createUnitCard(i, new Melee())

        case i if i >= 21 && i <= 30 =>
          createUnitCard(i, new Ranged())

        // Weather cards
        case i if i >= 31 && i <= 35 =>
          new BasicWeatherCard("Clear", new Clear)

        case i if i >= 36 && i <= 40 =>
          new BasicWeatherCard("Rain Storm", new Rain)

        case i if i >= 41 && i <= 45 =>
          new BasicWeatherCard("Frostbite", new Frost)

        case i if i >= 46 && i <= 50 =>
          new BasicWeatherCard("Heavy Fog", new Fog)
      }
    }
    fullDeck
  
  private def initializeGame(): Unit =
    println("Fase de Inicialización: Repartiendo Cartas")

    // Generate the deck and shuffle it.
    val fullDeck = generateFullDeck()
    val shuffledDeck = Random.shuffle(fullDeck)

    // Divide the deck of 50 into two of 25.
    val (p1DeckCards, pcDeckCards) = shuffledDeck.splitAt(25)
    val p1Deck = new Deck(scala.collection.mutable.ArrayBuffer.from(p1DeckCards))
    val pcDeck = new Deck(scala.collection.mutable.ArrayBuffer.from(pcDeckCards))
    
    // Create hands.
    val p1Hand = new Hand()
    val pcHand = new Hand()
    
    // Create players.
    controller.player = new Player("Jugador", controller.board.getPlayer1Area, p1Deck, p1Hand)
    controller.machine = new Player("Máquina", controller.board.getPlayer2Area, pcDeck, pcHand)
    
    // Draw 10 cards from the deck.
    drawCards(controller.player, 10)
    drawCards(controller.machine, 10)
    
    // The player starts the first round.
    controller.activePlayer = controller.player
    controller.roundNumber = 1
    println("Inicialización completa. Ronda 1 iniciada. Turno del Jugador")
    controller.state = new PlayerTurnState(controller)

  override def onEnter(): Unit = initializeGame()
  
  override def playCard(player: Player, card: Card, board: Board): Unit = throw new IllegalStateException("Error: No puedes usar playCard en este estado")

  override def passTurn(player: Player): Unit = throw new IllegalStateException("Error: No puedes usar passTurn en este estado")