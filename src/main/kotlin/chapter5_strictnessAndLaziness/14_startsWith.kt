package chapter5_strictnessAndLaziness

import chapter4_handlingErrors.Some

tailrec fun <A> Stream<A>.startsWith(that: Stream<A>): Boolean =
    when (this) {
        is Cons -> when (that) {
            is Cons ->
                if (this.head() == that.head())
                    this.tail().startsWith(that.tail())
                else false
            Empty -> true
        }
        Empty -> that is Empty
    }

fun <A> Stream<A>.startsWith2(that: Stream<A>) = this
    .zipAll(that)
    .takeWhile { it.second is Some }
    .forAll { (a, b) -> a == b }

fun main() {
    println(Stream.of(1, 2, 3).startsWith2(Stream.of(1, 2)))
    println(Stream.of(1, 2, 3).startsWith2(Stream.of(1, 2, 3)))
    println(Stream.of(1, 2, 3).startsWith2(Stream.of(1, 2, 3, 4)))
    println(Stream.of(1, 2, 3).startsWith2(Stream.of(1, 2, 99, 4)))
}