import org.junit.jupiter.api.Test

class PurposeModifierTest {

    @Test
    fun testMortgage() {
        assertRatesModifier(
            modifier = RatesModifier.PurposeModifier,
            request = testLoanRequest(loanPurpose = Purpose.Mortgage),
            expectedModifier = -0.02f,
        )
    }

    @Test
    fun testBusinessDevelopment() {
        assertRatesModifier(
            modifier = RatesModifier.PurposeModifier,
            request = testLoanRequest(loanPurpose = Purpose.BusinessDevelopment),
            expectedModifier = -0.005f,
        )
    }

    @Test
    fun testCarLoan() {
        assertRatesModifier(
            modifier = RatesModifier.PurposeModifier,
            request = testLoanRequest(loanPurpose = Purpose.CarLoan),
            expectedModifier = 0f,
        )
    }

    @Test
    fun testConsumer() {
        assertRatesModifier(
            modifier = RatesModifier.PurposeModifier,
            request = testLoanRequest(loanPurpose = Purpose.Consumer),
            expectedModifier = 0.015f,
        )
    }
}
