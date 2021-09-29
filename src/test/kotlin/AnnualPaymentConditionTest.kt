import org.junit.jupiter.api.Test

class AnnualPaymentConditionTest {

    @Test
    fun testLastYearIncome_More() {
        assertCondition<LoanCondition.Result.Approved>(
            condition = LoanCondition.AnnualPaymentCondition,
            request = testLoanRequest(
                lastYearIncome = 3,
                loanAmount = 1f,
                loanDuration = 1,
                incomeSource = IncomeSource.Employee,
                loanPurpose = Purpose.CarLoan,
                rating = 0,
            ),
        )
    }

    @Test
    fun testLastYearIncome_Equal() {
        assertCondition<LoanCondition.Result.Approved>(
            condition = LoanCondition.AnnualPaymentCondition,
            request = testLoanRequest(
                lastYearIncome = 1,
                loanAmount = 0.45417379f,
                loanDuration = 1,
                incomeSource = IncomeSource.Employee,
                loanPurpose = Purpose.CarLoan,
                rating = 0,
            ),
        )
    }

    @Test
    fun testLastYearIncome_Less() {
        assertCondition<LoanCondition.Result.Rejected>(
            condition = LoanCondition.AnnualPaymentCondition,
            request = testLoanRequest(
                lastYearIncome = 1,
                loanAmount = 1f,
                loanDuration = 1,
                incomeSource = IncomeSource.Employee,
                loanPurpose = Purpose.CarLoan,
                rating = 0,
            ),
        )
    }
}
