package chapter5_strictnessAndLaziness

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class _5_01_toListKtTest {
    @Test
    fun `qe`() {
        assertThat(1 + 1).isEqualTo(2)
    }
}