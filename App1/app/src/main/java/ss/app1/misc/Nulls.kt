package ss.app1.misc

import ss.app1.model.Card

fun main(args: Array<String>) {

    val x: Int = 2
    val y: Int? = 2

    val s: String? = "rrr"

    foo(dd = s ?: "Default")
    boo(dd = null)


}

fun foo(dd: String) {

}

fun boo(dd: String?) {

}

fun bla(c: Card?) {

    val cc: Card? = null
    val dd: Card? = Card(1, 1)
    val s = c?.suit

    val s1 = cc?.suit   //null
    val s2 = dd?.suit   //1

    cc?.deleteHardDrive()  //does nothing
    dd?.deleteHardDrive()  //runs deleteHardDrive


    println(cc!!.suit)


    println(cc.suit)


}

fun String?.camelCase(): String {

    return if (this == null) {
        "NULL"
    } else {
        this.toUpperCase()
    }


}


fun String?.camelCase1() = this?.toUpperCase() ?: "NULL"


