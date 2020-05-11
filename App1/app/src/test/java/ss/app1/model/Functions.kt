package ss.app1.model

fun f1() {
    println(111)
}

val f2 = {
    println(222)
}

val f3 = fun() {
    println(333)
}

fun threeTimes(f: () -> Unit): Unit {

    f()
    f()
    f()

}

//val  final const

fun main(args:Array<String>) {

    threeTimes(::f1)
    threeTimes(f2)
    threeTimes(f3)

}


