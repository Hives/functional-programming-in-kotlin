package chapter5_strictnessAndLaziness

fun <A, B> Stream<A>.foldRight(
    z: () -> B,
    f: (A, () -> B) -> B
): B =
    when (this) {
        is Cons -> f(this.head()) { tail().foldRight(z, f) }
        else -> z()
    }