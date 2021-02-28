package chapter5_strictnessAndLaziness

import chapter4_handlingErrors.Some

fun fibsUnfold() = unfold(Pair(0, 1)) { (n, m) -> Some(Pair(n, Pair(m, m + n))) }

fun fromUnfold(n: Int) = unfold(n) { m -> Some(Pair(m, m + 1)) }

fun <A> constantUnfold(c: A) = unfold(null) { Some(Pair(c, null))}


fun main() {
    println(fibsUnfold().take(10).toList())
    println(fromUnfold(100).take(10).toList())
    println(constantUnfold("hello").take(10).toList())
}
