package ss.app1.model

//immutable
class Hand private constructor(val cards: List<Card>, val name: String) {

    constructor(name: String) : this(listOf(), name)

    fun add(card: Card): Hand = Hand(cards + card, name)
    fun add(cards: List<Card>): Hand = Hand(this.cards + cards, name)

    val points get() = cards.sumBy { it.points }

    val size get() = cards.size

}
