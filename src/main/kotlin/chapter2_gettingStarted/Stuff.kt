package chapter2_gettingStarted

fun fib(i: Int): Int {
    tailrec fun f(n: Int, a: Int, b: Int): Int {
        if (n == 1) return a
        if (n == 2) return b
        else return f(n - 1, b, a + b)
    }

    return f(i, 0, 1)
}

val <T> List<T>.tail: List<T>
    get() = drop(1)
val <T> List<T>.head: T
    get() = first()

fun <A> isSorted(aa: List<A>, ordered: (A, A) -> Boolean): Boolean {
    fun go(x: A, xs: List<A>): Boolean =
        if (xs.isEmpty()) true
        else if (!ordered(x, xs.head)) false
        else go(xs.head, xs.tail)

    return aa.isEmpty() || go(aa.head, aa.tail)
}

fun <A, B, C> partial1(a: A, f: (A, B) -> C): (B) -> C =
    { b -> f(a, b) }

fun <A, B, C> curry(f: (A, B) -> C): (A) -> (B) -> C =
    { a -> { b -> f(a, b) } }

fun <A, B, C> uncurry(f: (A) -> (B) -> C): (A, B) -> C =
    { a, b -> f(a)(b) }

fun <A, B, C> compose(
    f: (B) -> C,
    g: (A) -> B
): (A) -> C =
    { a -> f(g(a)) }

fun main() {
    for (n in 1..20) {
        println(fib(n))
    }
}