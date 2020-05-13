package ss.app1.misc.builders

fun main(args: Array<String>) {

//    val root = html{
//        head = head{
//
//        }
//        body = body{
//
//        }
//    }

    val rootDiv = div {

        div {

        }

        div {

            div {

            }

        }

    }

    println(rootDiv.ser())

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
