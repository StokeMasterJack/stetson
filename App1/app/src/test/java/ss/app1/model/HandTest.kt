package ss.app1.model

import org.junit.Assert.assertEquals
import org.junit.Test

class HandTest {

    @Test
    fun cardValueSuit() {


    }


    @Test
    fun getPoints() {
        val h1 = Hand()
        assertEquals(0,h1.points)

//        h1.add(Card(value = 1,suit = 1))
//        h1.add(Card(value = 13,suit = 4))

//        assertEquals(11,h1.points)
    }
}