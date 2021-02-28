package chapter5_strictnessAndLaziness

fun fibs(): Stream<Int> {
    fun go(n: Int, m: Int): Stream<Int> =
        Cons({n}, {go(m, n + m)})

    return go(0, 1)
}

fun main() {
    println(fibs().take(10).toList())
}
