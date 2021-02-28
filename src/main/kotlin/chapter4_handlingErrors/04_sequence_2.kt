package chapter4_handlingErrors

import chapter3_functionalDataStructures.Cons
import chapter3_functionalDataStructures.List
import chapter3_functionalDataStructures.Nil
import chapter3_functionalDataStructures.foldRightL2
import chapter3_functionalDataStructures.map

fun <A> sequence2(
    xs: List<Option<A>>
): Option<List<A>> =
    foldRightL2(xs, Some(Nil)) { oa1: Option<A>, oa2: Option<List<A>> ->
        map2(oa1, oa2) { a1: A, a2: List<A> -> Cons(a1, a2) }
    }

fun main() {
    val options = List.of(1, 2, 3).map { Some(it) }
    println(sequence2(options))
    println(sequence2(Cons(None, options)))
}