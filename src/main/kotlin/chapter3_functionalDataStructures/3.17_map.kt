package chapter3_functionalDataStructures

fun <A, B> List<A>.map(f: (A) -> B): List<B> = List.foldRight(this, List.empty()) { x, xs -> ConsL(f(x), xs) }

fun main() {
    println(List.of(0, 1, 2, 3, 4).map { it * 2 })
    println(List.of(0, 1, 2, 3, 4).map { it % 2 == 0 })
}