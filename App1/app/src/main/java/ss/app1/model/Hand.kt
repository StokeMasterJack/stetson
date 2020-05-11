package ss.app1.model

//immutable
class Hand private constructor(val cards: List<Card>) {

    constructor() : this(listOf())

    fun add(card: Card): Hand = Hand(cards + card)
    fun add(cards: List<Card>): Hand = Hand(this.cards + cards)

    val points get() = cards.sumBy { it.points }

    val size get() = cards.size

}
