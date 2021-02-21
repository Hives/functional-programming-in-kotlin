package chapter4_handlingErrors

import chapter3_functionalDataStructures.Cons
import chapter3_functionalDataStructures.List
import chapter3_functionalDataStructures.Nil
import chapter3_functionalDataStructures.foldRightL2

fun <A, B> traverse(
    xa: List<A>,
    f: (A) -> Option<B>
): Option<List<B>> =
    foldRightL2(xa, Some(Nil)) { a: A, acc: Option<List<B>> ->
        map2(f(a), acc) { ob, obs -> Cons(ob, obs) }
    }

fun <A> sequence3(
    xs: List<Option<A>>
): Option<List<A>> =
    traverse(xs) { it }

fun main() {
    val options1 = List.of(1, 2, 3)
    val options2 = List.of(1, 2, null, 3)
    val f = { a: Int? -> if (a == null) None else Some(a) }
    println(traverse(options1, f))
    println(traverse(options2, f))
}
