package ss.app1.model

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CardTest {
    @Test
    fun cardValueSuit() {

        val c1 = Card(value = 1,suit = 1)

        //Card c1 = new Card(1,1)  // value: 1-13    suit: 1-5
        //foo(active = true,true,false,true)


        assertEquals(1, c1.value)
        assertEquals(1, c1.suit)


//        val (v,s) = c1
        val v = c1.value
        val s = c1.suit

        val c2 = c1.copy(value = 2)
        println(c2)

        val c3 = Card(0)
        assertEquals(1, c3.value)
        assertEquals(1, c3.suit)
        assertEquals(c1, c3)


        try {
            val c4 = Card(1,22)
            fail()
        } catch (e: IllegalArgumentException) {
            //OK
        }


    }

    /*
    points = value
    j,q,k = 10
     */
    @Test
    fun getPoints() {
        val c1 = Card(value = 1,suit = 1)    //Ace of Spades
        val c2 = Card(value = 4,suit = 2)   //4 of Hearts
        val c3 = Card(value = 13,suit = 4)  //king of Diamonds

        assertEquals("Ace",c1.valueName)
        assertEquals("4",c2.valueName)
        assertEquals("King",c3.valueName)

        assertEquals("Spades",c1.suitName)
        assertEquals("Hearts",c2.suitName)
        assertEquals("Diamonds",c3.suitName)

        assertEquals(1,c1.points)
        assertEquals(4,c2.points)
        assertEquals(10,c3.points)

        assertEquals("Ace of Spaded",c1.name)
        assertEquals("4 of Hearts",c2.name)
        assertEquals("King of Diamonds",c3.name)
    }
}