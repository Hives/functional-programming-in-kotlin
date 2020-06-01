package chapter3

sealed class List<out A> {
    companion object {
        fun <A> of(vararg aa: A): List<A> {
            val tail = aa.sliceArray(1 until aa.size)
            return if (aa.isEmpty()) Nil else Cons(aa[0], of(*tail))
        }

        fun sum(ints: List<Int>): Int =
            when (ints) {
                is Nil -> 0
                is Cons -> ints.head + sum(ints.tail)
            }

        fun product(doubles: List<Double>): Double =
            when (doubles) {
                is Nil -> 1.0
                is Cons ->
                    if (doubles.head == 0.0) 0.0
                    else doubles.head * product(doubles.tail)
            }
    }
}

fun <A> List<A>.tail(): List<A> =
    when (this) {
        is Nil -> throw Exception("List was Nil")
        is Cons -> tail
    }

fun <A> List<A>.setHead(x: A): List<A> =
    when (this) {
        is Nil -> throw Exception("List was Nil")
        is Cons -> Cons(x, tail)
    }

fun <A> List<A>.drop(n: Int): List<A> =
    if (n == 0) this
    else {
        when (this) {
            is Nil -> throw Exception("Ran out of elements")
            is Cons -> tail.drop(n - 1)
        }
    }

fun <A> List<A>.dropWhile(f: (A) -> Boolean): List<A> =
    when (this) {
        is Nil -> throw Exception("Ran out of elements")
        is Cons ->
            if (f(head)) tail.dropWhile(f)
            else this
    }

object Nil : List<Nothing>()

data class Cons<out A>(val head: A, val tail: List<A>) : List<A>()

fun main() {
    val a = Cons(0, Cons(1, Cons(2, Cons(3, Nil))))
    val isEven = { n: Int -> n % 2 == 0 }
    println(a.dropWhile(isEven))
}