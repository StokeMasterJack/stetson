package ss.app1.model

//immutable
class Hand private constructor(val cards: List<Card>) {

    constructor() : this(listOf())

    fun add(card: Card): Hand = Hand(cards + card)

    val points get() = cards.sumBy { it.points }

}
