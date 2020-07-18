package chapter3_functionalDataStructures

tailrec fun <A> List<A>.startsWith(xs: List<A>): Boolean =
    when (xs) {
        is Nil -> true
        is Cons -> when (this) {
            is Nil -> false
            is Cons ->
                if (this.head() == xs.head()) this.tail().startsWith(xs.tail())
                else false
        }
    }

tailrec fun <A> List<A>.hasSubsequence(xs: List<A>): Boolean =
    when (this) {
        is Nil -> false
        is Cons ->
            if (this.startsWith(xs)) true
            else this.tail().hasSubsequence(xs)
    }

fun main() {
    println(List.empty<Int>().startsWith(List.empty()))
    println(List.empty<Int>().startsWith(List.of(1)))
    println(List.of(0, 1, 2, 3).startsWith(List.empty()))
    println(List.of(0, 1, 2, 3).startsWith(List.of(0)))
    println(List.of(0, 1, 2, 3).startsWith(List.of(0, 1)))
    println(List.of(0, 1, 2, 3).startsWith(List.of(1)))

    println(List.of(0, 1, 2, 3).hasSubsequence(List.of(1)))
    println(List.of(0, 1, 2, 3).hasSubsequence(List.of(1, 2)))
    println(List.of(0, 1, 2, 3).hasSubsequence(List.of(0, 1, 2)))
    println(List.of(0, 1, 2, 3).hasSubsequence(List.of(0, 1, 2, 3)))
    println(List.of(0, 1, 2, 3).hasSubsequence(List.of(1, 2, 4)))
    println(List.empty<Int>().hasSubsequence(List.of(1)))
}