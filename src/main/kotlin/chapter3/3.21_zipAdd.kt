package chapter3

fun zipAdd(xs: List<Int>, ys: List<Int>): List<Int> =
    if (xs == Nil || ys == Nil) Nil
    else Cons(xs.head() + ys.head(), zipAdd(xs.tail(), ys.tail()))

fun main() {
    println(zipAdd(List.of(0,1,2,3), List.of(4,5,6,7)))
}