package ss.app1.model

@Suppress("DataClassPrivateConstructor")
data class Game private constructor(
    val shuffle: Boolean = true,
    private val deck: Deck = Deck(shuffle = shuffle),
    private var phi: Hand = Hand(name = "Player"),
    private var dhi: Hand = Hand(name = "Dealer"),
    private var isStayInternal: Boolean = false
) {

    constructor(sh: Boolean = true) : this(shuffle = sh) {
        deal()
    }

//    init {
//        deal()
//    }

    fun copy(): Game {
        return Game(
            shuffle = shuffle,
            deck = deck.copy(),
            phi = phi,
            dhi = dhi,
            isStayInternal = isStayInternal
        )
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
        phi = Hand(phi.name).add(deck.takeCards(2))
        dhi = Hand(dhi.name).add(deck.takeCards(2))
        isStayInternal = false
    }

    val ph: Hand get() = phi
    val dh: Hand get() = dhi

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

    val isStay: Boolean get() = isStayInternal


    companion object {
        const val pressHitOrStay = "Press Hit or Stay"
        const val dealerWins = "Dealer Wins!"
        const val playerWins = "Player Wins!"
    }
}
