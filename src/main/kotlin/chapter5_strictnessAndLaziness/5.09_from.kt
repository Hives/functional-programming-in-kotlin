package chapter5_strictnessAndLaziness

fun from(n: Int): Stream<Int> =
    Cons({ n }, { from(n + 1) })

fun main() {
    println(from(10).take(5).toList())
}
