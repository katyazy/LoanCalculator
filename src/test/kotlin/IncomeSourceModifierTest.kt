import org.junit.jupiter.api.Test

class IncomeSourceModifierTest {

    @Test
    fun testPassive() {
        assertRatesModifier(
            modifier = RatesModifier.IncomeSourceModifier,
            request = testLoanRequest(incomeSource = IncomeSource.Passive),
            expectedModifier = 0.005f,
        )
    }

    @Test
    fun testEmployee() {
        assertRatesModifier(
            modifier = RatesModifier.IncomeSourceModifier,
            request = testLoanRequest(incomeSource = IncomeSource.Employee),
            expectedModifier = -0.0025f,
        )
    }

    @Test
    fun testIndividualEntrepreneur() {
        assertRatesModifier(
            modifier = RatesModifier.IncomeSourceModifier,
            request = testLoanRequest(incomeSource = IncomeSource.IndividualEntrepreneur),
            expectedModifier = 0.0025f,
        )
    }

    @Test
    fun testJobless() {
        assertRatesModifier(
            modifier = RatesModifier.IncomeSourceModifier,
            request = testLoanRequest(incomeSource = IncomeSource.Jobless),
            expectedModifier = 0f,
        )
    }
}
