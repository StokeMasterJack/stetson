package ss.app1.model

class Game(shuffle: Boolean = true) {


    var deck = Deck(shuffle = shuffle)

    var ph: Hand = Hand()
    var dh: Hand = Hand()

    init {
        deal()
    }

    fun hit() {
        ph = ph.add(deck.takeCard())
    }

    fun stay() {
        while (dh.points <= 17) {
            dh = dh.add(deck.takeCard())
        }
    }

    fun deal() {
        ph = Hand().add(deck.takeCards(2))
        dh = Hand().add(deck.takeCards(2))
    }
}
