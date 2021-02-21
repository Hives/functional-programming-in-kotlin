package chapter5_strictnessAndLaziness

fun <A> Stream<A>.takeWhile(p: (A) -> Boolean): Stream<A> =
    when (this) {
        is Empty -> Empty
        is Cons ->
            if (p(head())) Cons(head, { tail().takeWhile(p) })
            else Empty
    }

fun main() {
    val a = Stream.of(2, 4, 3, 4, 5, 6, 7)

    println(a.toList())
    println(a.takeWhile { it % 2 == 0 }.toList())
}
