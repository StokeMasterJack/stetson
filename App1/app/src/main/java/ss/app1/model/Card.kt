package ss.app1.model

import androidx.compose.Immutable

//immutable
@Immutable
data class Card(val value: Int, val suit: Int) {

    init {
//        val valueRange = IntRange(start = 1, endInclusive = 13)
//        val valueRange = 1..13

        require(value in 1..13)
        require(suit in 1..4)
    }

    /**
     * @param index 0 to 51
     */
    constructor(index: Int) : this(index % 13 + 1, index / 13 + 1) {
        require(index in 0..51)
    }

    val suitName: String
        get() {
            return when (suit) {
                1 -> "Spades"
                2 -> "Hearts"
                3 -> "Clubs"
                4 -> "Diamonds"
                else -> throw IllegalStateException("Invalid suit: $suit")
            }
        }

//    val valueName: String
//        get() {
//            return when (value) {
//                1 -> "Ace"
//                in 2..10 -> value.toString()
//                11 -> "Jack"
//                12 -> "Queen"
//                13 -> "King"
//                else -> throw IllegalStateException("Invalid value: $value")
//            }
//        }

    val valueName: String
        get() {
            check(value in 1..13)
            return when (value) {
                1 -> "Ace"
                11 -> "Jack"
                12 -> "Queen"
                13 -> "King"
                else -> value.toString()
            }
        }

    val points: Int
        get() {
            return when (value) {
                11, 12, 13 -> 10
                else -> value
            }
        }

    val name get() = "$valueName of $suitName"


    fun deleteHardDrive() {
        println("deleteHardDrive")
    }

//    val name  = "$valueName or $suitName"


}