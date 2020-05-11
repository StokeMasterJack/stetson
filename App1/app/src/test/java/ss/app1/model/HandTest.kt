package ss.app1.model

import org.junit.Assert.assertEquals
import org.junit.Test

class HandTest {

    @Test
    fun getPoints() {
        val h1 = Hand()
        assertEquals(0, h1.points)

        val h2 = h1.add(Card(value = 1, suit = 1))
        val h3 = h2.add(Card(value = 13, suit = 4))

        assertEquals(11, h3.points)

        h1.cards.forEach {
            println(it.name)
        }


    }
}