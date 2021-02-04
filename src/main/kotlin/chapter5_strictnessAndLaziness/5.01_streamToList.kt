package chapter5_strictnessAndLaziness

import chapter3_functionalDataStructures.ConsL
import chapter3_functionalDataStructures.List
import chapter3_functionalDataStructures.NilL
import chapter3_functionalDataStructures.reverseL

fun <A> Stream<A>.toList(): List<A> {
    tailrec fun go(xs: Stream<A>, acc: List<A>): List<A> =
            when (xs) {
                is Cons -> go(xs.tail(), ConsL(xs.head(), acc))
                is Empty -> acc
            }

    return go(this, NilL).reverseL()
}

fun main() {
    val a = Stream.of(1, 2, 3, 4, 5, 6, 7)

    println(a.toList())
}