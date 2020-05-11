package ss.app1.model

import org.junit.Assert.assertEquals
import org.junit.Test

class DeckTest {

    @Test
    fun deck1() {

        val d1 = Deck(shuffle = false)
        assertEquals(52,d1.size)

        val c1 = d1.takeCard()
        assertEquals(51,d1.size)
        assertEquals("Ace of Spades",c1.name)

        val c2 = d1.takeCard()
        assertEquals(50,d1.size)
        assertEquals("2 of Spades",c2.name)

        println(d1.shuffle)

        println(d1.cards.size)

        val (c3,c4) = d1.takeCards(2)

        assertEquals("3 of Spades",c3.name)
        assertEquals("4 of Spades",c4.name)
    }
}