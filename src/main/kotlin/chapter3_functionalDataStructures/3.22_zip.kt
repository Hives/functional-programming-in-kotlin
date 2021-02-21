package chapter3_functionalDataStructures

fun <X, Y, Z> zipWith(xs: List<X>, ys: List<Y>, f: (X, Y) -> Z): List<Z> =
    if (xs == Nil || ys == Nil) Nil
    else Cons(f(xs.head(), ys.head()), zipWith(xs.tail(), ys.tail(), f))

fun main() {
    println(zipWith(List.of(0, 1, 2, 3), List.of(4, 5, 6, 7)) { x, y -> x * y })
}
