package ss.app1.model

import org.junit.Assert.assertEquals
import org.junit.Test



/*


Game
    d:Deck
    ph:Hand   //playerHand
    dh:Hand   //dealerHand

    hit()     //hitPlayer: adds one card to player hand

    stay()    //hit dealer hand, one card at a time, as long dh.points <= 17

    deal()


initial deal:
    2 cards in each hand
 */
class GameTest {

    @Test
    fun game() {
        val g1 = Game(shuffle = false)

        assertEquals(2,g1.ph.size)  //a,2
        assertEquals(2,g1.dh.size)  //3,4

        assertEquals(3,g1.ph.points)  //a,2
        assertEquals(7,g1.dh.points)  //3,4

        g1.hit()
        assertEquals(3,g1.ph.size)  //a,2
        assertEquals(2,g1.dh.size)  //3,4

        assertEquals(8,g1.ph.points)  //a,2,5
        assertEquals(7,g1.dh.points)  //3,4

        g1.stay()
        assertEquals(3,g1.ph.size)  //a,2
        assertEquals(4,g1.dh.size)  //3,4

        assertEquals(8,g1.ph.points)  //a,2,5
        assertEquals(20,g1.dh.points)  //3,4, 6, 7

        g1.deal()
        assertEquals(2,g1.ph.size)  //8,9
        assertEquals(2,g1.dh.size)  //10,J

        assertEquals(17,g1.ph.points)  //a,2,5
        assertEquals(20,g1.dh.points)  //3,4, 6,7
    }
}