package chapter4_handlingErrors

import chapter3_functionalDataStructures.ConsL
import chapter3_functionalDataStructures.List
import chapter3_functionalDataStructures.NilL

fun <A> sequence(
    options: List<Option<A>>
): Option<List<A>> {
    tailrec fun go(xs: List<Option<A>>, acc: List<A>): Option<List<A>> =
        when (xs) {
            is NilL -> Some(acc)
            is ConsL ->
                when (xs.head) {
                    is None -> None
                    is Some -> go(xs.tail, acc.append(xs.head.get))
                }
        }

    return go(options, List.empty())
}

fun <A> List<A>.append(z: A): List<A> = List.foldRight(this, ConsL(z, NilL)) { a, b -> ConsL(a, b) }

fun main() {
    val options1 = List.of(Some(1), Some(2), Some(3), Some(4))
    println(sequence(options1))
}