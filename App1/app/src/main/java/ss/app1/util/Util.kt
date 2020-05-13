package ss.app1.util

import androidx.compose.Composable
import ss.app1.model.Card
import ss.app1.model.abc

typealias VF = () -> Unit
typealias CF = @Composable() () -> Unit


fun main(args: Array<String>) {
    val c = Card(1,1)

    println(c.abc)
}