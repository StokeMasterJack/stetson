package ss.app1.misc.builders

const val NL = "\n"
const val indentSize = 4

class Div(val children: MutableList<Div> = mutableListOf())

fun StringBuilder.tab(depth: Int) = repeat(depth * indentSize) { append(' ') }

fun Div.ser(depth: Int = 0): String {
    val sb = StringBuilder()
    sb.tab(depth)
    sb.append("<div>$NL")

    children.forEach {
        val childSer = it.ser(depth = depth + 1)
        sb.append(childSer)
    }
    sb.tab(depth)
    sb.append("</div>$NL")

    return sb.toString()

}
fun div(f: Div.() -> Unit): Div {
    val d = Div()
    d.f()
    return d
}

fun Div.div(f: Div.() -> Unit): Div {
    val d = Div()
    d.f()
    children.add(d)
    return d
}