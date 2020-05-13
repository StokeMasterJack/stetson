package ss.app1.misc.builders


fun indent(depth: Int) = List(depth) { "  " }.joinToString(separator = "")
//fun StringBuilder.tab(depth:Int) = append(indent(depth))