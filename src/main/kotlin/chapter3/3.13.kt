package chapter3

fun <A> append(a1: List<A>, a2: List<A>): List<A> =
    List.foldRight(a1, a2) { x, xs -> Cons(x, xs) }

fun main() {
    val xs = List.of(1, 2, 3, 4, 5)
    val ys = List.of(6, 7, 8, 9)
    println(append(xs, ys))
}