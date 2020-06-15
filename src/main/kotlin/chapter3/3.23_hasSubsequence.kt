package chapter3

tailrec fun <A> List<A>.startsWith(xs: List<A>): Boolean =
    if (xs == Nil) true
    else if (this == Nil) false
    else if (this.head() == xs.head()) this.tail().startsWith(xs.tail())
    else false

tailrec fun <A> List<A>.hasSubsequence(xs: List<A>): Boolean =
    if (xs == Nil) true
    else if (this.startsWith(xs)) true
    else this.tail().hasSubsequence(xs)

fun main() {
    println(List.of(0, 1, 2, 3).hasSubsequence(List.of(1)))
}