package chapter3_functionalDataStructures

fun <A> depth(tree: Tree<A>): Int =
    when (tree) {
        is Leaf -> 0
        is Branch -> 1 + maxOf(depth(tree.left), depth(tree.right))
    }

fun main() {
    println(depth(Leaf(100)))
    println(depth(Branch(Leaf(23), Branch(Leaf(999), Branch(Leaf(3), Leaf(777))))))
}