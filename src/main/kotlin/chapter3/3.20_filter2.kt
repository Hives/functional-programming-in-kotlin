package chapter3

// implement filter using flatMap

fun <A> List<A>.filter2(f: (A) -> Boolean) = this.flatMap {
    if (f(it)) List.of(it)
    else List.empty()
}

fun main() {
    println(List.of(0, 1, 2, 3, 4).filter2 { it % 2 == 0 })
    println(List.of("zero", "one", "two", "three", "four").filter2 { it.length > 3 })
}
