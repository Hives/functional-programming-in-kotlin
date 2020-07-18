package chapter3_functionalDataStructures

fun <A, B> map(tree: Tree<A>, f: (A) -> B): Tree<B> =
    when (tree) {
        is Leaf -> Leaf(f(tree.value))
        is Branch -> Branch(map(tree.left, f), map(tree.right, f))
    }

fun main() {
    println(map(Leaf(100), { it * 2 }))
    println(map(Branch(Leaf(23), Branch(Leaf(999), Branch(Leaf(3), Leaf(777)))), { it * 2 }))
}
