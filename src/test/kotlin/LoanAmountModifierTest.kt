import org.junit.jupiter.api.Test

class LoanAmountModifierTest {

    @Test
    fun test0point1() {
        assertRatesModifier(
            modifier = RatesModifier.AmountModifier,
            request = testLoanRequest(loanAmount = 0.1f),
            expectedModifier = 0.01f,
        )
    }

    @Test
    fun test1() {
        assertRatesModifier(
            modifier = RatesModifier.AmountModifier,
            request = testLoanRequest(loanAmount = 1f),
            expectedModifier = 0f,
        )
    }

    @Test
    fun test10() {
        assertRatesModifier(
            modifier = RatesModifier.AmountModifier,
            request = testLoanRequest(loanAmount = 10f),
            expectedModifier = -0.01f,
        )
    }

    @Test
    fun test100() {
        assertRatesModifier(
            modifier = RatesModifier.AmountModifier,
            request = testLoanRequest(loanAmount = 100f),
            expectedModifier = -0.02f,
        )
    }
}
