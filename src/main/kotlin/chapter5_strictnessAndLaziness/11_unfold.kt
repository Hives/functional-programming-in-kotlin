package chapter5_strictnessAndLaziness

import chapter4_handlingErrors.Option
import chapter4_handlingErrors.Some
import chapter4_handlingErrors.getOrElse
import chapter4_handlingErrors.map

fun <A, S> unfold(state: S, f: (S) -> Option<Pair<A, S>>): Stream<A> =
    f(state).map { (value, newState) ->
        Cons({ value }, { unfold(newState, f) })
    }.getOrElse {
        Stream.empty()
    }

fun main() {
    val fibsy = unfold(Pair(0, 1)) { (n, m) -> Some(Pair(n, Pair(m, m + n))) }
    println(fibsy.take(10).toList())
}