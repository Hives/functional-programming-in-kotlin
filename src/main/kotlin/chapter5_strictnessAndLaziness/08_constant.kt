package chapter5_strictnessAndLaziness

fun <A> constant(a: A): Stream<A> = Cons({ a }, { constant(a) })

fun main() {
    println(constant("hello").take(5).toList())
}