package builders

import java.util.*

@DslMarker
annotation class HBuild

private const val indentSize = 4
private val tab = " ".repeat(indentSize)
private val nl = System.getProperty("line.separator")!!
private fun indent(depth: Int) = tab.repeat(depth)

fun List<HBuilder>.ser(depth: Int = 0): String = joinToString(separator = "") { it.ser(depth + 1) }

typealias H = HBuilder.Companion  //create an empty NodeBuilder

typealias F<T> = T.() -> Unit        //add the middle stuff to that node builder
typealias BFactory<T> = () -> T

@HBuild
sealed class HBuilder {

    val nodes: MutableList<HBuilder> = mutableListOf<HBuilder>()

    open fun ser(depth: Int = 0): String {
        val dent = indent(depth)
        return when (this) {
            is TextBuilder -> "ddd"
            is ElBuilder -> "$dent<$name>$nl${nodes.ser(depth + 1)}$nl$dent</$name>$nl"
        }
    }

    companion object {

        fun html1(head: F<HeadBuilder>, body: F<BodyBuilder>): HtmlBuilder1 {
            val hb = HeadBuilder().apply(head)
            val bb = BodyBuilder().apply(body)
            return HtmlBuilder1(hb, bb)
        }

        fun html(f: F<HtmlBuilder>): HtmlBuilder = el(f, ::HtmlBuilder)

        fun <T : ElBuilder> el(f: F<T>, mkBuilder: BFactory<T>): T = mkBuilder().apply(f)


    }

}


sealed class TextBuilder : HBuilder() {

}

sealed class ElBuilder : HBuilder() {

    val name: String
        get() = this.javaClass.simpleName.replace("Builder", "").toLowerCase(Locale.ROOT)

    fun <C : ElBuilder> el(f: F<C>, mkBuilder: BFactory<C>) {
        nodes.add(H.el(f, mkBuilder))
    }


}

sealed class BaseEl : ElBuilder() {
    fun span(f: SpanBuilder.() -> Unit) = el(f, ::SpanBuilder)


}

class Hand {


    companion object {
//        fun from(rs:ResultSet):Person{
//
//        }
//
//        fun of(vararg card: Card): Hand {
//        }
    }
}


//a function that returns a function
fun <C : ElBuilder> mkFun(bf: () -> C, addNode: (C) -> Unit): (f: F<C>) -> Unit =
    { addNode(bf().apply(it)) }

sealed class FlowElBuilder : BaseEl() {

//    fun div(f: DivBuilder.() -> Unit) = el(f, ::DivBuilder)

    fun <C : ElBuilder> add(c: C) {
        nodes.add(c)
    }

    val div = mkFun(::DivBuilder, ::add)


}

sealed class EmptyElBuilder : ElBuilder()

class DivBuilder : FlowElBuilder()
class SpanBuilder : BaseEl()

class HtmlBuilder1(hb: HeadBuilder, bb: BodyBuilder) : ElBuilder() {

    init {
        nodes.add(hb)
        nodes.add(bb)
    }

}

class HtmlBuilder : ElBuilder() {

    fun head(f: HeadBuilder.() -> Unit) {
        check(nodes.isEmpty())
        el(f, ::HeadBuilder)
    }

    fun body(f: BodyBuilder.() -> Unit) = el(f, ::BodyBuilder)
}

class BodyBuilder : FlowElBuilder()
class HeadBuilder : EmptyElBuilder()

fun main(args: Array<String>) {

    val b1: HBuilder = HBuilder.html1(
        head = {

        },
        body = {
            div {
                div {

                }

                div {

                }

                handVu()

                span {

                }
                div {

                }
            }
        }
    )

    println(b1.ser())


//    val b: HBuilder = HBuilder.html {
//
//        head {
//
//        }
//
//        body {
//            div {
//                div {
//
//                }
//
//                span {
//
//                }
//                div {
//
//                }
//            }
//        }
//
//    }

//    println(b.ser())

}

private fun DivBuilder.handVu() {
    div {
        span { }
        span { }
    }
}