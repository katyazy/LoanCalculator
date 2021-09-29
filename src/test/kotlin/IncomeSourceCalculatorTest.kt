import org.junit.jupiter.api.Test

class IncomeSourceCalculatorTest {

    @Test
    fun testPassive() {
        assertAmountCalculator(
            calculator = AmountCalculator.IncomeSourceCalculator,
            request = testLoanRequest(incomeSource = IncomeSource.Passive),
            expectedAmount = Constants.LOAN_AMOUNT_INCOME_PASSIVE,
        )
    }

    @Test
    fun testEmployee() {
        assertAmountCalculator(
            calculator = AmountCalculator.IncomeSourceCalculator,
            request = testLoanRequest(incomeSource = IncomeSource.Employee),
            expectedAmount = Constants.LOAN_AMOUNT_INCOME_EMPLOYEE,
        )
    }

    @Test
    fun testIndividualEntrepreneur() {
        assertAmountCalculator(
            calculator = AmountCalculator.IncomeSourceCalculator,
            request = testLoanRequest(incomeSource = IncomeSource.IndividualEntrepreneur),
            expectedAmount = Constants.LOAN_AMOUNT_INCOME_IP,
        )
    }
}
