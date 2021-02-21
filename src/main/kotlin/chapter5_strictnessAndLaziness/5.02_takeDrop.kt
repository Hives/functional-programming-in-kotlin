package chapter5_strictnessAndLaziness

fun <A> Stream<A>.take(n: Int): Stream<A> =
        when (this) {
            is Empty -> Empty
            is Cons ->
                if (n == 0) Empty
                else Cons(head, { tail().take(n - 1) })
        }

tailrec fun <A> Stream<A>.drop(n: Int): Stream<A> =
        when (this) {
            is Empty -> Empty
            is Cons ->
                if (n == 0) this
                else {
                    tail().drop(n - 1)
                }
        }

fun main() {
    val a = Stream.of(1, 2, 3, 4, 5, 6, 7)

    println(a.toList())
    println(a.take(5).toList())
    println(a.drop(5).toList())
}
