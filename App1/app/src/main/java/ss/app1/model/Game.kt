package ss.app1.model

class Game(shuffle: Boolean = true) {

    private val deck = Deck(shuffle = shuffle)

    private var phi: Hand = Hand()
    private var dhi: Hand = Hand()

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
    }

    fun deal() {
        phi = Hand().add(deck.takeCards(2))
        dhi = Hand().add(deck.takeCards(2))
    }

    val ph:Hand get() = phi
    val dh:Hand get() = dhi


}
