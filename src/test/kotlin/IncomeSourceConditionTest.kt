import org.junit.jupiter.api.Test

class IncomeSourceConditionTest {

    @Test
    fun testIncomeSource_Jobless() {
        assertCondition<LoanCondition.Result.Rejected>(
            condition = LoanCondition.IncomeSourceCondition,
            request = testLoanRequest(incomeSource = IncomeSource.Jobless)
        )
    }

    @Test
    fun testIncomeSource_Passive() {
        assertCondition<LoanCondition.Result.Approved>(
            condition = LoanCondition.IncomeSourceCondition,
            request = testLoanRequest(incomeSource = IncomeSource.Passive)
        )
    }

    @Test
    fun testIncomeSource_Employee() {
        assertCondition<LoanCondition.Result.Approved>(
            condition = LoanCondition.IncomeSourceCondition,
            request = testLoanRequest(incomeSource = IncomeSource.Employee)
        )
    }

    @Test
    fun testIncomeSource_IndividualEntrepreneur() {
        assertCondition<LoanCondition.Result.Approved>(
            condition = LoanCondition.IncomeSourceCondition,
            request = testLoanRequest(incomeSource = IncomeSource.IndividualEntrepreneur)
        )
    }
}
