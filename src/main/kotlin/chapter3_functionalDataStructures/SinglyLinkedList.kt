package chapter3_functionalDataStructures

sealed class List<out A> {
    companion object {
        fun <A> of(vararg aa: A): List<A> {
            val tail = aa.sliceArray(1 until aa.size)
            return if (aa.isEmpty()) Nil else Cons(aa[0], of(*tail))
        }

        fun <A> empty(): List<A> = Nil

        fun sum(ints: List<Int>): Int = foldLeft(ints, 0) { a, b -> a + b }

        fun product(dbs: List<Double>): Double = foldLeft(dbs, 1.0) { a, b -> a * b }

        fun <A, B> foldRight(xs: List<A>, acc: B, f: (A, B) -> B): B =
            when (xs) {
                is Nil -> acc
                is Cons -> f(xs.head, foldRight(xs.tail, acc, f))
            }

        tailrec fun <A, B> foldLeft(xs: List<A>, acc: B, f: (B, A) -> B): B =
            when (xs) {
                is Nil -> acc
                is Cons -> foldLeft(xs.tail, f(acc, xs.head), f)
            }
    }
}

object Nil : List<Nothing>()

data class Cons<out A>(val head: A, val tail: List<A>) : List<A>()

fun <A> List<A>.tail(): List<A> {
    require(this is Cons) { "Can't get tail of Nil" }
    return tail
}

fun <A> List<A>.head(): A {
    require(this is Cons) { "Can't get head of Nil" }
    return head
}

fun <A> List<A>.setHead(x: A): List<A> {
    require(this is Cons) { "Can't setHead of Nil" }
    return Cons(x, tail)
}

fun <A> List<A>.drop(n: Int): List<A> =
    if (n == 0) this
    else {
        when (this) {
            is Nil -> Nil
            is Cons -> tail.drop(n - 1)
        }
    }

fun <A> List<A>.dropWhile(f: (A) -> Boolean): List<A> =
    when (this) {
        is Nil -> Nil
        is Cons ->
            if (f(head)) tail.dropWhile(f)
            else this
    }

fun <A> List<A>.append(xs: List<A>): List<A> =
    when (this) {
        is Nil -> xs
        is Cons -> Cons(head, tail.append(xs))
    }

fun <A> List<A>.init(): List<A> {
    require(this is Cons) { "Can't get init of Nil" }
    return when (tail) {
        is Nil -> Nil
        is Cons -> Cons(head, tail.init())
    }
}

fun <A> List<A>.length(): Int = List.foldRight(this, 0) { _, acc -> acc + 1 }

fun <A> List<A>.reverseL(): List<A> = List.foldLeft(this, List.empty()) { acc, a -> Cons(a, acc) }

fun <A> List<A>.reverseR(): List<A> {
    fun f(a: A, b: List<A>): List<A> =
        when (b) {
            is Nil -> Cons(a, b)
            else -> Cons(b.head(), f(a, b.tail()))
        }
    return List.foldRight(this, List.empty()) { a, acc -> f(a, acc) }
}

fun main() {
    val a = List.of(1, 2, 3, 4)
    val b = List.of(4.0, 5.0, 6.0, 7.0, 8.0)

    println(List.sum(a))
    println(List.product(b))

    println(a.reverseL())
    println(a.reverseR())

    println(List.foldRight(a, List.empty<Int>()) { a, b -> Cons(a, b) })
    println(List.foldLeft(a, List.empty<Int>()) { a, b -> Cons(b, a) })
}