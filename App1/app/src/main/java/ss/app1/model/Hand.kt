package ss.app1.model

class Hand(val cards:MutableList<Card> = mutableListOf()) {

    val points3:Int get() {
        var p = 0
        for (card in cards) {
            p+=card.points
        }
        return p
    }

    val points get() = cards.sumBy { it.points }


}
