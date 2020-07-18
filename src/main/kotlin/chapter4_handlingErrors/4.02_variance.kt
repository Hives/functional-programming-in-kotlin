package chapter4_handlingErrors

import kotlin.math.pow

fun mean(xs: List<Double>): Option<Double> =
    if (xs.isEmpty()) None
    else Some(xs.sum() / xs.size)

fun variance(xs: List<Double>): Option<Double> =
    mean(xs)
        .flatMap { mean ->
            mean(xs.map { x -> (x - mean).pow(2) })
        }
