package chapter3

fun <A> concat(xxs: List<List<A>>): List<A> =
    foldRightL2(xxs, Nil, ::append)

fun main() {
    println(
        concat(
            List.of(
                List.of(0, 1, 2),
                List.of(3, 4, 5),
                List.of(6, 7, 8)
            )
        )
    )
}