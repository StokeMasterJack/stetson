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

    init {
//        (1..13).forEach { v ->
//            (1..4).forEach { s ->
//                val c = Card(v, s)
//                cardsMutable.add(c)
//            }
//        }

//        for (v in (1..13)) {
//            for (s in (1..4)) {
//                val c = Card(v, s)
//                cardsMutable.add(c)
//            }
//        }


//        val list2 = (0..51).map { Card(it) }.run { if(shuffle) shuffled() else this  }
//
//        val list3 = if(shuffle){
//            list2.shuffled()
//        }else{
//            list2
//        }
//
//        val list4 = list3.toMutableList()


//        val list2 = (0..51).map(::Card)
    }


    fun takeCard(): Card = takeCards(1)[0]

    fun takeCards(n: Int): List<Card> {
        val ret: List<Card> = cardsMutable.take(n)
        repeat(n) { cardsMutable.removeAt(0) }
        return ret
    }

    val size: Int get() = cardsMutable.size

    val cards: List<Card> = cardsMutable


}

