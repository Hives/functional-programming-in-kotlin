package chapter3_functionalDataStructures

fun <A, B> List<A>.flatMap(f: (A) -> List<B>) =
    List.foldRight(this, List.empty<B>()) { x, xa ->
       append(f(x), xa)
    }

fun main() {
    println(List.of(0,1,2,3).flatMap { n -> List.of(n, n) })
}

// fr(Cons(0, Cons(1, Cons(2, Nil))), Nil, f)
// f(0, fr(Cons(1, Cons(2, Nil)), Nil, f))
// f(0, f(1, fr(Cons(2, Nil), Nil, f)))
// f(0, f(1, f(2, fr(Nil, Nil, f))))
// f(0, f(1, f(2, Nil)))
// append(List.of(0, 0), append(List.of(1, 1), append(List.of(2, 2), Nil)))
// List.of(0, 0, 1, 1, 2, 2)
