package chapter5_strictnessAndLaziness

import chapter3_functionalDataStructures.List
import chapter3_functionalDataStructures.Nil

fun <A> Stream<A>.toList(): List<A> = when(this) {
    is Cons -> chapter3_functionalDataStructures.Cons(this.h(), t().toList())
    is Empty -> Nil
}