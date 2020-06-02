package chapter3

sealed class List<out A> {
    companion object {
        fun <A> of(vararg aa: A): List<A> {
            val tail = aa.sliceArray(1 until aa.size)
            return if (aa.isEmpty()) Nil else Cons(aa[0], of(*tail))
        }

        fun sum(ints: List<Int>): Int = foldRight(ints, 0, { a, b -> a + b })

        fun product(dbs: List<Double>): Double = foldRight(dbs, 1.0, { a, b -> a * b })

        fun <A, B> foldRight(xs: List<A>, acc: B, f: (A, B) -> B): B =
            when (xs) {
                is Nil -> acc
                is Cons -> f(xs.head, foldRight(xs.tail, acc, f))
            }
    }
}

object Nil : List<Nothing>()

data class Cons<out A>(val head: A, val tail: List<A>) : List<A>()

fun <A> List<A>.tail(): List<A> {
    require(this is Cons) { "Can't get tail of Nil" }
    return tail
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

fun main() {
    val a = Cons(1, Cons(2, Cons(3, Cons(4, Nil))))
    val b = Cons(1.0, Cons(2.0, Cons(3.0, Cons(4.0, Nil))))

    println(List.sum(a))
    println(List.product(b))
}