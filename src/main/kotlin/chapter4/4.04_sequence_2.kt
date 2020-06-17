package chapter4

// this is more like how they did it in the book. except they used a foldRight,
// and they used the linked list class created in chapter 3

fun <A> sequence(
    xs: List<Option<A>>
): Option<List<A>> =
    xs.fold(Some(emptyList<A>()) as Option<List<A>>) { acc, x ->
        map2(acc, x) { acc, x -> acc + x }
    }

fun main() {
    val options1 = listOf(1, 2, 3).map { Some(it) }
    val options2 = listOf(4, 5, 6).map { Some(it) }
    val options3 = options1 + None + options2
    println(sequence(options3))
}