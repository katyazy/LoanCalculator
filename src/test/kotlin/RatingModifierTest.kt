import org.junit.jupiter.api.Test

class RatingModifierTest {

    @Test
    fun testMinus1() {
        assertRatesModifier(
            modifier = RatesModifier.RatingModifier,
            request = testLoanRequest(rating = -1),
            expectedModifier = 0.015f,
        )
    }

    @Test
    fun test0() {
        assertRatesModifier(
            modifier = RatesModifier.RatingModifier,
            request = testLoanRequest(rating = 0),
            expectedModifier = 0f,
        )
    }

    @Test
    fun test1() {
        assertRatesModifier(
            modifier = RatesModifier.RatingModifier,
            request = testLoanRequest(rating = 1),
            expectedModifier = -0.0025f,
        )
    }

    @Test
    fun test2() {
        assertRatesModifier(
            modifier = RatesModifier.RatingModifier,
            request = testLoanRequest(rating = 2),
            expectedModifier = -0.0075f,
        )
    }
}
