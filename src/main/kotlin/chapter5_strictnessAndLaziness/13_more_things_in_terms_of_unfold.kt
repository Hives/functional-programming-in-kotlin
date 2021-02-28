package chapter5_strictnessAndLaziness

import chapter4_handlingErrors.None
import chapter4_handlingErrors.Option
import chapter4_handlingErrors.Some

fun <A, B> Stream<A>.mapUnfold(f: (A) -> B): Stream<B> =
    unfold(this) {
        when (it) {
            is Cons -> Some(Pair(f(it.head()), it.tail()))
            Empty -> None
        }
    }

fun <A> Stream<A>.takeUnfold(n: Int): Stream<A> =
    unfold(this) { stream ->
        when (stream) {
            is Cons ->
                if (n > 0) Some(Pair(stream.head(), stream.tail().takeUnfold(n - 1)))
                else None
            Empty -> None
        }
    }

fun <A> Stream<A>.takeWhileUnfold(p: (A) -> Boolean): Stream<A> =
    unfold(this) {
        when (it) {
            is Cons ->
                if (p(it.head())) Some(Pair(it.head(), it.tail()))
                else None
            Empty -> None
        }
    }

fun <X, Y, Z> Stream<X>.zipWith(that: Stream<Y>, f: (X, Y) -> Z): Stream<Z> =
    unfold(Pair(this, that)) { (xs, ys) ->
        when (xs) {
            is Cons -> when (ys) {
                is Cons -> Some(
                    Pair(
                        f(xs.head(), ys.head()),
                        Pair(xs.tail(), ys.tail())
                    )
                )
                Empty -> None
            }
            Empty -> None
        }
    }

fun <X, Y> Stream<X>.zipAll(that: Stream<Y>): Stream<Pair<Option<X>, Option<Y>>> =
    unfold(Pair(this, that)) { (xs, ys) ->
        when(xs){
            is Cons -> when(ys) {
                is Cons -> Some(
                    Pair(
                        Pair(Some(xs.head()), Some(ys.head())),
                        Pair(xs.tail(), ys.tail())
                    )
                )
                Empty -> Some(
                    Pair(
                        Pair(Some(xs.head()), None),
                        Pair(xs.tail(), Empty)
                    )
                )
            }
            Empty -> when(ys) {
                is Cons ->Some(
                    Pair(
                        Pair(None, Some(ys.head())),
                        Pair(Empty, ys.tail())
                    )
                )
                Empty -> None
            }
        }
    }


fun main() {
    val a = Stream.of(2, 4, 3, 4, 5, 6)
    val b = Stream.of('a', 'b', 'c')

    println(a.mapUnfold { it * 2 }.toList())
    println(a.takeUnfold(3).toList())
    println(a.takeWhileUnfold { it % 2 == 0 }.toList())
    println(a.zipWith(b) { x, y -> Pair(x, y) }.toList())
    println(a.zipAll(b).toList())
    println(b.zipAll(a).toList())
}
