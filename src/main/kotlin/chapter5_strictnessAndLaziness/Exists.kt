package chapter5_strictnessAndLaziness

fun <A> Stream<A>.exists(p: (A) -> Boolean): Boolean =
    foldRight({false}) { h, t -> p(h) || t() }

fun main() {
    val a = Stream.of(1, 2, 3, 4)

    println(a.exists { it == 4 })
    println(a.exists { it == 5 })
}