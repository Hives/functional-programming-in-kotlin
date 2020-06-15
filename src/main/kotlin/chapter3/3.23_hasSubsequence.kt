package chapter3

fun <A> List<A>.hasSubsequence(xs: List<A>): Boolean =
    if (xs == Nil) true
    else if (this == Nil) false
    else if (this.head() == xs.head()) this.tail().hasSubsequence(xs.tail()) || this.tail().hasSubsequence(xs)
    else this.tail().hasSubsequence(xs)

fun main() {
    println(List.of(0, 1, 2, 3).hasSubsequence(List.of(1, 2, 3)))
}