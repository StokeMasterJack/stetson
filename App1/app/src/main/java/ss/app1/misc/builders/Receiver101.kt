package ss.app1.misc.builders

fun f1(a: Int): Int {
    return a * a
}

fun takeF1(f: (Int) -> Int) {
    f(33)
}

class Point(val x: Int, val y: Int) {

    fun area(a: Int): Int {
        return (a + a) + x + y
    }

}

fun takeF2(f: Point.(Int) -> Int) {

    val p: Point = Point(1, 1)
    val ret = p.f(33)
    println("ret = ${ret}")


}



//receiver
fun main(args: Array<String>) {

    val ff1: Point.(Int) -> Int = Point::area

    val ff2: Point.(Int) -> Int = { a ->
//        it   //type of it: Int
//        this //type of this: Point

        a * a + x + y


//        ret //type of ret must be: Int
    }

    takeF2(ff1)
    takeF2(ff2)


}