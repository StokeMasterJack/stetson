package ss.app1.model

class HandMutable() {

    private val cardsMutable: MutableList<Card> = mutableListOf()

    fun add(card: Card) = cardsMutable.add(card)

    val points3: Int
        get() {
            var p = 0
            for (card in cardsMutable) {
                p += card.points
            }
            return p
        }

    val points get() = cardsMutable.sumBy { it.points }

    val cards: List<Card> get() = cardsMutable

//    val cards:List<Card> get() = Collections.unmodifiableList(cardsMutable)

    fun f1(): Unit {
        println(cards)
        println(cardsMutable)

        val cheat = cards as MutableList<Card>
        cheat.clear()
    }

//    fun addCard(card: Card):Hand{
//        return Hand(cardsMutable + card)
//    }

}
