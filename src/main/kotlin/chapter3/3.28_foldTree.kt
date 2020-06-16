package chapter3

fun <A, B> fold(ta: Tree<A>, l: (A) -> B, b: (B, B) -> B): B =
    when (ta) {
        is Leaf -> l(ta.value)
        is Branch -> b(fold(ta.left, l, b), fold(ta.right, l, b))
    }

fun <A> sizeF(ta: Tree<A>): Int = fold(ta, { 1 }, { b1, b2 -> 1 + b1 + b2 })

fun maximumF(ta: Tree<Int>): Int = fold(ta, { it }, { b1, b2 -> maxOf(b1, b2) })

fun <A> depthF(ta: Tree<A>): Int = fold(ta, { 0 }, { b1, b2 -> 1 + maxOf(b1, b2) })

fun <A, B> mapF(ta: Tree<A>, f: (A) -> B): Tree<B> =
    fold(ta, { Leaf(f(it)) }, { b1: Tree<B>, b2: Tree<B> -> Branch(b1, b2) })

fun main() {
    println(sizeF(Leaf("foo")))
    println(sizeF(Branch(Leaf("foo"), Branch(Leaf("bar"), Leaf("baz")))))

    println(maximumF(Leaf(100)))
    println(maximumF(Branch(Leaf(23), Branch(Leaf(999), Branch(Leaf(3), Leaf(777))))))

    println(depthF(Leaf(100)))
    println(depthF(Branch(Leaf(23), Branch(Leaf(999), Branch(Leaf(3), Leaf(777))))))

    println(mapF(Leaf(100), { it * 2 }))
    println(mapF(Branch(Leaf(23), Branch(Leaf(999), Branch(Leaf(3), Leaf(777)))), { it * 2 }))
}
