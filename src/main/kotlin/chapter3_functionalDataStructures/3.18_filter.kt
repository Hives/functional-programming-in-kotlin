package chapter3_functionalDataStructures

fun <A> List<A>.filter(f: (A) -> Boolean) =
    List.foldRight(this, List.empty<A>()) { x, xa ->
        if (f(x)) ConsL(x, xa)
        else xa
    }

fun main() {
    println(List.of(0, 1, 2, 3, 4).filter { it % 2 == 0 })
    println(List.of("zero", "one", "two", "three", "four").filter { it.length > 3 })
}