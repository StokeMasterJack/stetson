package ss.app1.misc.builders

//StringBuilder

/*
fun takeF2(f: Point.(Int) -> Int) {

    val p: Point = Point(1, 1)
    val ret = p.f(33)
    println("ret = ${ret}")


}

 */
//buildString2(f: StringBuilder.() -> Unit  )
//   buildString2:
//      returns a String
//   takes one argument of type function f
//            f returns Unit
//            f takes zero args
//            f takes a StringBuffer as the receiver
//

fun buildString2(f: StringBuilder.() -> Unit): String {
    val sb = StringBuilder()  //empty

    sb.f()

    //filled with good stuff
    return sb.toString()
}


fun main(args: Array<String>) {
    val s: String = buildString2 {
        append("aaa")
        append("bb")
        append("cc")
    }
    println(s)  //aaabbcc

}

//fun main(args: Array<String>) {
//    val sb = StringBuffer()
//    sb.append()
//}