package chapter3

fun maximum(tree: Tree<Int>): Int =
    when (tree) {
        is Leaf -> tree.value
        is Branch -> maxOf(maximum(tree.left), maximum(tree.right))
    }

fun main() {
    println(maximum(Leaf(100)))
    println(maximum(Branch(Leaf(23), Branch(Leaf(999), Branch(Leaf(3), Leaf(777))))))
}