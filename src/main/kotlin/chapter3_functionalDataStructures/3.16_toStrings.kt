package chapter3_functionalDataStructures

fun toStrings(xs: List<Double>) = List.foldRight(xs, List.empty<String>()) { d, ds -> ConsL(d.toString(), ds)}

fun main() {
    println(toStrings(List.of(1.1, 2.2, 3.3, 4.4)))
}