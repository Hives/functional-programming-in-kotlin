package chapter3

// this is how they do it in the book

fun <A, B> foldLeftR2(xs: List<A>, z: B, f: (B, A) -> B): B =
    List.foldRight(xs, { b: B -> b }, { a, g -> { b -> g(f(b, a)) } })(z)

fun <A, B> foldRightL2(xs: List<A>, z: B, f: (A, B) -> B): B =
    List.foldLeft(xs, { b: B -> b }, { g, a -> { b -> g(f(a, b)) } })(z)

// foldLeft([1,2,3], {it}, f)(z)
// foldLeft([2,3], f({it}, 1), f)(z)
// foldLeft([3], f(f({it}, 1), 2), f)(z)
// foldLeft([], f(f(f({it}, 1), 2), 3), f)(z)
// f(f(f({it}, 1), 2), 3)(z)
//
// f = { g, a -> { b -> g(h(a, b)) } }
// f(f({ b -> h(1, b)}, 2), 3)(z)
// f(f({ b -> h(1, b)}, 2), 3)(z)
// f({ b' -> { b -> h(1, b) }(h(2, b') }, 3)(z)
// f({ b' -> h(1, h(2, b')) }, 3)(z)
// { b" -> { b' -> h(1, h(2, b')) }(h(3, b") }(z)
// { b" -> h(1, h(2, h(3, b"))) }(z)
// h(1, h(2, h(3, z)))
// foldRight([1,2,3], z, h)

fun <A, B> foldLeftRDemystified(
    ls: List<A>,
    acc: B,
    combiner: (B, A) -> B
): B {
    fun delayer(a: A, bToB: (B) -> B) =
        fun(b: B) = bToB(combiner(b, a))

    val chain = List.foldRight(ls, { it }, ::delayer)

    return chain(acc)
}

// foldLeft([1,2,3,4], z, f)
// foldLeft([2,3,4], f(z, 1), f)
// foldLeft([3,4], f(f(z, 1), 2), f)
// foldLeft([4], f(f(f(z, 1), 2), 3), f)
// foldLeft([], f(f(f(f(z, 1), 2), 3), 4), f)
// f(f(f(f(z, 1), 2, 3, 4)

// foldRight([1,2,3,4], z, f)
// f(1, foldRight([2,3,4], z, f))
// f(1, f(2, foldRight([3,4], z, f)))
// f(1, f(2, f(3, foldRight([4], z, f))))
// f(1, f(2, f(3, f(4, foldRight([], z, f)))))
// f(1, f(2, f(3, f(4, z))))
//
// z = identity; f = delayer; combiner = g
// delayer(a, bToB) = { b -> bToB(g(b,a)) }
//
// f(1, f(2, f(3, delayer(4, z))))
// f(1, f(2, f(3, { b1 -> g(b1, 4) } ))
// f(1, f(2, { b2 -> { b1 -> g(b1, 4) }(g(b2, a)) } ))
// f(1, f(2, { b2 -> g(g(b2, 3), 4) } ))
// f(1, { b3 -> g(g(g(b3, 2), 3), 4) } ))
// { b4 -> g(g(g(g(b4, 1), 2), 3), 4) }
