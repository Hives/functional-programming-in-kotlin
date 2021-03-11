package chapter5_strictnessAndLaziness

import chapter3_functionalDataStructures.map
import chapter4_handlingErrors.None
import chapter4_handlingErrors.Some

fun <A> Stream<A>.tails(): Stream<Stream<A>> =
    unfold(this) { stream ->
        when (stream) {
            is Cons -> Some(Pair(stream, stream.tail()))
            Empty -> None
        }
    }.append { Stream.empty() }

fun main() {
    val a = Stream.of(1, 2, 3, 4)
    println(a.tails().toList().map { it.toList() })
}