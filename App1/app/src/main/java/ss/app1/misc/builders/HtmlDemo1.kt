package ss.app1.misc.builders


//context
//dd.foo(2,3)
fun main(args: Array<String>) {

    val rootDiv = div {

        div {
            div {

            }
        }

        div {

            div {

            }


        }

    }

    println(rootDiv.ser())

    fun f1(): Unit {

    }

    val f2 = {

    }

    val f3 = fun() {

    }

}

data class User(
    val userName: String,
    val firstName: String,
    val lastName: String
)

fun mai1(args: Array<String>) {

    val b1 = User("df", "dave", "Ford")
    val b2 = User("df", "dave", "Ford")

    div {
        with(b2) {
            println(firstName)
            println(firstName)
        }
    }


}

class F0 : () -> Unit {
    override operator fun invoke() {
        println(0)
    }
}

val f0 = F0()


fun main() {
    f0()
}

/*
<div>
    <div>
    </div>
    <div>
        <div>
        </div>
    </div>
</div>
 */




