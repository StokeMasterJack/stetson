package ss.app1.model

//.let { if(shuffle) it.shuffled() else it  }.toMutableList()
class Deck private constructor(
    val shuffle: Boolean,
    private val cardsMutable: MutableList<Card>
) {

    constructor(shuffle: Boolean = true) : this(shuffle = shuffle,
        cardsMutable = (0..51).map { Card(it) }.run { if (shuffle) shuffled() else this }
            .toMutableList()
    )

    fun takeCard(): Card = takeCards(1)[0]

    fun takeCards(n: Int): List<Card> {
        val ret: List<Card> = cardsMutable.take(n)
        repeat(n) { cardsMutable.removeAt(0) }
        return ret
    }

    fun copy(): Deck {
        return Deck(
            shuffle = shuffle,
            cardsMutable = ArrayList(cardsMutable)
        )
    }

    val size: Int get() = cardsMutable.size

    val cards: List<Card> = cardsMutable


}

