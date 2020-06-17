package chapter4

import chapter3.Cons
import chapter3.List
import chapter3.Nil

fun <A> sequence(
    options: List<Option<A>>
): Option<List<A>> {
    tailrec fun go(xs: List<Option<A>>, acc: List<A>): Option<List<A>> =
        when (xs) {
            is Nil -> Some(acc)
            is Cons ->
                when (xs.head) {
                    is None -> None
                    is Some -> go(xs.tail, acc.append(xs.head.get))
                }
        }

    return go(options, List.empty())
}

fun <A> List<A>.append(z: A): List<A> = List.foldRight(this, Cons(z, Nil)) { a, b -> Cons(a, b) }

fun main() {
    val options1 = List.of(Some(1), Some(2), Some(3), Some(4))
    println(sequence(options1))
}