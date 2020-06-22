package chapter4

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import chapter3.foldRightL2

fun <A, B, E> List<A>.traverse(
    f: (A) -> Either<E, B>
): Either<E, List<B>> =
    foldRightL2(this, Right(Nil)) { a: A, acc: Either<E, List<B>> ->
        map2(f(a), acc) { ob, obs -> Cons(ob, obs) }
    }

fun <A, E> List<Either<E, A>>.sequence(): Either<E, List<A>> =
    this.traverse { it }

fun main() {
    val options1 = List.of(2, 4, 6)
    val options2 = List.of(2, 3, 4, 5, 6, 7)
    val f = { n: Int -> if (n % 2 == 0) Right(n) else Left(n) }
    println(options1.traverse(f))
    println(options2.traverse(f))
}