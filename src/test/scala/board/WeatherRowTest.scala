package board
import munit.FunSuite
import model.board.*
import model.card.weathercard.BasicWeatherCard
import model.card.weathercard.weathereffect.{Clear, Fog}

class WeatherRowTest extends FunSuite{
  var row1: WeatherRow = _
  var row2: WeatherRow = _
  var card1: BasicWeatherCard = _
  var card2: BasicWeatherCard = _
  override def beforeEach(context: BeforeEach):Unit= {
    row1 = new WeatherRow()
    row2 = new WeatherRow()
    card1 = new BasicWeatherCard("Fog", new Fog)
    card2 = new BasicWeatherCard("Clear", new Clear)
  }

  test("01 addCard and getCard Test") {
    assert(row1.getCard.isEmpty) //Initial state of a row test.

    row1.addCard(card1) //Add card1 to row1.
    assertEquals(row1.getCard, Some(card1))

    row1.addCard(card2) //The row can only contain one card.
    assertEquals(row1.getCard, Some(card1)) //Then, there should be no changes in the row, i.e. it still contains card1.
  }

  test("02 clearCard") {
    row1.addCard(card1) //Add card1 to row1.
    assertEquals(row1.getCard, Some(card1))

    row1.clearCard() //Clear the row.
    assert(row1.getCard.isEmpty) //The row is empty.
  }

}
