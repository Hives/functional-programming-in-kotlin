package chapter3_functionalDataStructures

fun addOne(xs: List<Int>) =
   List.foldRight(xs, List.empty<Int>()) { y, ys -> ConsL(y + 1, ys) }

fun main() {
    println(addOne(List.of(0, 1, 2, 3, 4, 5)))
}