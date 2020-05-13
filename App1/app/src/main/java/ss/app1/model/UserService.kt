package ss.app1.model

//val UserService:UserService = ss.app1.model.UserService()


interface Foo {
    fun bla()
}

object UserService : Foo {

    override fun bla() {
        TODO("Not yet implemented")
    }

    val x: Int = 22
    val y: Int = 44


    fun ff() {
        println("ff")
    }

}

class Person(val id: Int, val fn: String) {


    companion object {

    }
}


fun Card.imageName(): String {
    return "$value-$suit.jpg"
}

val Card.abc: String get() = "abc"

fun main(args: Array<String>) {
    println(UserService.x)
    println(UserService.y)
    println(UserService.ff())
}