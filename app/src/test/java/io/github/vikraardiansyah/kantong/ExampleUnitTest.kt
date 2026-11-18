package io.github.vikraardiansyah.kantong

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun toRupiah_formatsThousands() {
        assertEquals("Rp500", 500L.toRupiah())
        assertEquals("Rp25.000", 25_000L.toRupiah())
        assertEquals("Rp1.000.000", 1_000_000L.toRupiah())
    }
}