import org.junit.jupiter.api.Test

class LoanAmountConditionTest {

    @Test
    fun testLastYearIncome_More() {
        assertCondition<LoanCondition.Result.Approved>(
            condition = LoanCondition.LoanAmountCondition,
            request = testLoanRequest(lastYearIncome = 3, loanAmount = 0.9f, loanDuration = 1),
        )
    }

    @Test
    fun testLastYearIncome_Equal() {
        assertCondition<LoanCondition.Result.Approved>(
            condition = LoanCondition.LoanAmountCondition,
            request = testLoanRequest(lastYearIncome = 3, loanAmount = 1f, loanDuration = 1),
        )
    }

    @Test
    fun testLastYearIncome_Less() {
        assertCondition<LoanCondition.Result.Rejected>(
            condition = LoanCondition.LoanAmountCondition,
            request = testLoanRequest(lastYearIncome = 3, loanAmount = 2f, loanDuration = 1),
        )
    }
}
