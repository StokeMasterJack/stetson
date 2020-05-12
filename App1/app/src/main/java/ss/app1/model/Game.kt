package ss.app1.model

class Game(shuffle: Boolean = true) {

    private val deck = Deck(shuffle = shuffle)

    private var phi: Hand = Hand()
    private var dhi: Hand = Hand()

    private var isStayInternal:Boolean = false

    init {
        deal()
    }

    fun hit() {
        phi = phi.add(deck.takeCard())
    }

    fun stay() {
        while (dhi.points <= 17) {
            dhi = dhi.add(deck.takeCard())
        }
        isStayInternal = true
    }

    fun deal() {
        phi = Hand().add(deck.takeCards(2))
        dhi = Hand().add(deck.takeCards(2))
        isStayInternal = false
    }

    val ph:Hand get() = phi
    val dh:Hand get() = dhi

    val isGameOver: Boolean get() = isStayInternal || ph.points >= 21

    val msg: String
        get() = if (isGameOver) {
            when {
                ph.points > 21 -> dealerWins
                dh.points > 21 -> playerWins
                ph.points == dh.points -> dealerWins
                ph.points > dh.points -> playerWins
                dh.points > ph.points -> dealerWins
                else -> throw IllegalStateException()
            }
        } else {
            pressHitOrStay
        }

    val isStay:Boolean get() = isStayInternal


    companion object {
        const val pressHitOrStay = "Press Hit or Stay"
        const val dealerWins = "Dealer Wins!"
        const val playerWins = "Player Wins!"


    }
}
