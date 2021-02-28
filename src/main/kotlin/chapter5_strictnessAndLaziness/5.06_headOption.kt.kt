package chapter5_strictnessAndLaziness

import chapter4_handlingErrors.Option
import chapter4_handlingErrors.Some

fun <A> Stream<A>.headOption(): Option<A> =
    foldRight({ Option.empty() }, { h, _ -> Some(h) })

fun main() {
    val a = Stream.of(2, 4, 3, 4, 5, 6, 7)
    val b = Stream.of<Int>()

    println(a.headOption())
    println(b.headOption())
}
