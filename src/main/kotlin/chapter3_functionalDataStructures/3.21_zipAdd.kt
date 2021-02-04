package chapter3_functionalDataStructures

fun zipAdd(xs: List<Int>, ys: List<Int>): List<Int> =
    if (xs == NilL || ys == NilL) NilL
    else ConsL(xs.head() + ys.head(), zipAdd(xs.tail(), ys.tail()))

fun main() {
    println(zipAdd(List.of(0,1,2,3), List.of(4,5,6,7)))
}