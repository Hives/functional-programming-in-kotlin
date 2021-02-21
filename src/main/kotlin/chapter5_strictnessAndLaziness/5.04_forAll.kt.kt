package chapter5_strictnessAndLaziness

//tailrec fun <A> Stream<A>.forAll(p: (A) -> Boolean): Boolean =
//    when (this) {
//        is Cons ->
//            if (!p(head())) false
//            else tail().forAll(p)
//        Empty -> true
//    }

fun <A> Stream<A>.forAll(p: (A) -> Boolean): Boolean =
    foldRight({true}, {a, b -> p(a) && b() })

fun main() {
    val a = Stream.of(2, 4, 3, 4, 5, 6, 7)
    val b = Stream.of(2, 4, 6, 8, 10)
    println(a.forAll { it < 10 })
    println(b.forAll { it < 10 })
    println(a.forAll { it % 2 == 0 })
    println(b.forAll { it % 2 == 0 })
}