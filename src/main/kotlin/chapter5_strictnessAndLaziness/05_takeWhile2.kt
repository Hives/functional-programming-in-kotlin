package chapter5_strictnessAndLaziness

import chapter5_strictnessAndLaziness.Stream.Companion.empty

fun <A> Stream<A>.takeWhileFold(p: (A) -> Boolean): Stream<A> =
    foldRight({ empty() },
        { h, t -> if (p(h)) Cons({h}, t) else empty() })

fun main() {
    val a = Stream.of(2, 4, 3, 4, 5, 6, 7)

    println(a.toList())
    println(a.takeWhileFold { it % 2 == 0 }.toList())
}
