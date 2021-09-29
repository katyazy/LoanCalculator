import org.junit.jupiter.api.Test

class RateModifiersTest {

    @Test
    fun testEmployeeRatingMinus1MillionCarLoan() {
        assertRates(
            request = testLoanRequest(
                incomeSource = IncomeSource.Employee,
                rating = 0,
                loanAmount = 0.1f,
                loanDuration = 1,
                loanPurpose = Purpose.CarLoan,
            ),
            expectedRate = 0.1075f,
        )
    }

    @Test
    fun testPassiveRating0MillionConsumer() {
        assertRates(
            request = testLoanRequest(
                incomeSource = IncomeSource.Passive,
                rating = -1,
                loanAmount = 1f,
                loanDuration = 1,
                loanPurpose = Purpose.Consumer,
            ),
            expectedRate = 0.135f,
        )
    }

    @Test
    fun testEmployee() {
        assertRates(
            request = testLoanRequest(
                incomeSource = IncomeSource.Employee,
                rating = 1,
                loanAmount = 10f,
                loanDuration = 1,
                loanPurpose = Purpose.Mortgage,
            ),
            expectedRate = 0.065f,
        )
    }

    @Test
    fun testIndividualEntrepreneur() {
        assertRates(
            request = testLoanRequest(
                incomeSource = IncomeSource.IndividualEntrepreneur,
                rating = 2,
                loanAmount = 100f,
                loanDuration = 1,
                loanPurpose = Purpose.BusinessDevelopment,
            ),
            expectedRate = 0.07f,
        )
    }
}
