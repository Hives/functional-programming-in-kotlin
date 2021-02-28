package chapter5_strictnessAndLaziness

fun <A, B> Stream<A>.map(f: (A) -> B): Stream<B> =
    foldRight({ Stream.empty() }) { h, t ->
        Cons({ f(h) }, t)
    }

fun <A> Stream<A>.filter(f: (A) -> Boolean): Stream<A> =
    foldRight({ Stream.empty() }) { h, t ->
        if (f(h)) Cons({ h }, t)
        else t()
    }

fun <A> Stream<A>.append(x: () -> Stream<A>): Stream<A> =
    foldRight(x) { h, t -> Cons({ h }, t) }

fun <A, B> Stream<A>.flatMap(f: (A) -> Stream<B>): Stream<B> =
    foldRight({ Stream.empty() }) { h, t ->
        f(h).append(t)
    }


fun main() {
    val a = Stream.of(2, 4, 3, 4, 5, 6)

    println(a.map { it * 2 }.toList())
    println(a.filter { it % 2 == 0 }.toList())
    println(a.append { Stream.of(100, 101, 102) }.toList())
    println(a.flatMap { Cons({ it }, { Cons({ 99 }, { Empty }) }) }.toList())
}
