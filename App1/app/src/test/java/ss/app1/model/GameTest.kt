package ss.app1.model

import org.junit.Assert.*
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
        val g1 = Game(sh = false)

        assertEquals(2,g1.ph.size)  //a,2
        assertEquals(2,g1.dh.size)  //3,4

        assertEquals(3,g1.ph.points)  //a,2
        assertEquals(7,g1.dh.points)  //3,4

        assertFalse(g1.isStay)
        assertFalse(g1.isGameOver)
        assertEquals(Game.pressHitOrStay, g1.msg)

        g1.hit()
        assertEquals(3,g1.ph.size)  //a,2
        assertEquals(2,g1.dh.size)  //3,4

        assertFalse(g1.isStay)
        assertFalse(g1.isGameOver)
        assertEquals(Game.pressHitOrStay, g1.msg)

        assertEquals(8,g1.ph.points)  //a,2,5
        assertEquals(7,g1.dh.points)  //3,4

        assertFalse(g1.isStay)
        assertFalse(g1.isGameOver)
        assertEquals(Game.pressHitOrStay, g1.msg)

        g1.stay()
        assertEquals(3,g1.ph.size)  //a,2
        assertEquals(4,g1.dh.size)  //3,4

        assertEquals(8,g1.ph.points)  //a,2,5
        assertEquals(20,g1.dh.points)  //3,4, 6, 7

        assertTrue(g1.isStay)
        assertTrue(g1.isGameOver)
        assertEquals(Game.dealerWins, g1.msg)

        g1.deal()
        assertEquals(2,g1.ph.size)  //8,9
        assertEquals(2,g1.dh.size)  //10,J

        assertEquals(17,g1.ph.points)  //a,2,5
        assertEquals(20,g1.dh.points)  //3,4, 6,7

        assertFalse(g1.isStay)
        assertFalse(g1.isGameOver)
        assertEquals(Game.pressHitOrStay, g1.msg)

        val g1Copy = g1.copy()

        assertEquals(2,g1Copy.ph.size)  //8,9
        assertEquals(2,g1Copy.dh.size)  //10,J

        assertEquals(17,g1Copy.ph.points)  //a,2,5
        assertEquals(20,g1Copy.dh.points)  //3,4, 6,7

        assertFalse(g1Copy.isStay)
        assertFalse(g1Copy.isGameOver)
        assertEquals(Game.pressHitOrStay, g1Copy.msg)

    }

    @Test
    fun test() {
        val g1 = Game(sh = false)
        assertEquals(2,g1.ph.size)  //a,2
        assertEquals(2,g1.dh.size)  //3,4

        assertEquals(3,g1.ph.points)  //a,2
        assertEquals(7,g1.dh.points)  //3,4

        assertFalse(g1.isStay)
        assertFalse(g1.isGameOver)
        assertEquals(Game.pressHitOrStay, g1.msg)

        val g2 = g1.copy()
        g2.hit()

        assertEquals(3,g2.ph.size)  //a,2
        assertEquals(2,g2.dh.size)  //3,4

        assertFalse(g2.isStay)
        assertFalse(g2.isGameOver)
        assertEquals(Game.pressHitOrStay, g1.msg)

        assertEquals(8,g2.ph.points)  //a,2,5
        assertEquals(7,g2.dh.points)  //3,4

        assertFalse(g2.isStay)
        assertFalse(g2.isGameOver)
        assertEquals(Game.pressHitOrStay, g2.msg)
    }
}