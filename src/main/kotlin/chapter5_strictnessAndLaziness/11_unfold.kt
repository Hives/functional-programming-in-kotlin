package chapter5_strictnessAndLaziness

import chapter4_handlingErrors.None
import chapter4_handlingErrors.Option
import chapter4_handlingErrors.Some

fun <A, S> unfold(z: S, f: (S) -> Option<Pair<A, S>>): Stream<A> {
    val foo = f(z)
    return when (foo) {
        None -> Stream.empty()
        is Some -> {
            val (bar, baz) = foo.get
            Cons({ bar }, { unfold(baz, f) })
        }
    }
}

fun main() {
    val fibsy = unfold(Pair(0, 1)) { (n, m) -> Some(Pair(n, Pair(m, m + n))) }
    println(fibsy.take(10).toList())
}