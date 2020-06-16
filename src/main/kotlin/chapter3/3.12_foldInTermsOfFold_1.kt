package chapter3

fun <A, B> foldRightL(xs: List<A>, acc: B, f: (A, B) -> B): B {
    val reversedXs = List.foldLeft(xs, List.empty<A>()) { z, a -> Cons(a, z) }
    return List.foldLeft(reversedXs, acc) { b, a -> f(a, b) }
}

fun <A, B> foldLeftR(xs: List<A>, acc: B, f: (B, A) -> B): B {
    fun g(a: A, b: List<A>): List<A> =
        when (b) {
            is Nil -> Cons(a, b)
            else -> Cons(b.head(), g(a, b.tail()))
        }

    val reversedXs = List.foldRight(xs, List.empty<A>()) { a, acc -> g(a, acc) }

    return List.foldRight(reversedXs, acc) { a, b -> f(b, a) }
}

fun main() {
    val a = List.of(1, 2, 3, 4)
    val b = List.of(4.0, 5.0, 6.0, 7.0, 8.0)

    println(List.sum(a))
    println(List.product(b))

    println(a.reverseL())
    println(a.reverseR())

    println(List.foldRight(a, List.empty<Int>()) { a, b -> Cons(a, b) })
    println(foldRightL(a, List.empty<Int>()) { a, b -> Cons(a, b) })
    println(List.foldLeft(a, List.empty<Int>()) { a, b -> Cons(b, a) })
    println(foldLeftR(a, List.empty<Int>()) { a, b -> Cons(b, a) })
}
