import org.junit.jupiter.api.Test
import kotlin.test.assertIs

class LoanEngineTest {

    @Test
    fun testRejectedRequestedAmountBiggerThanAllowedIncomeSourcePassive() {
        assertIs<LoanEngine.LoanResult.Rejected>(
            value = LoanEngineImplementation.process(
                request = testLoanRequest(
                    incomeSource = IncomeSource.Passive,
                    loanAmount = Constants.LOAN_AMOUNT_INCOME_PASSIVE + 0.1f,
                )
            )
        )
    }

    @Test
    fun testRejectedRequestedAmountBiggerThanAllowedIncomeSourceEmployee() {
        assertIs<LoanEngine.LoanResult.Rejected>(
            value = LoanEngineImplementation.process(
                request = testLoanRequest(
                    incomeSource = IncomeSource.Employee,
                    loanAmount = Constants.LOAN_AMOUNT_INCOME_EMPLOYEE + 0.1f,
                )
            )
        )
    }

    @Test
    fun testRejectedRequestedAmountBiggerThanAllowedIncomeSourceIndividualEntrepreneur() {
        assertIs<LoanEngine.LoanResult.Rejected>(
            value = LoanEngineImplementation.process(
                request = testLoanRequest(
                    incomeSource = IncomeSource.IndividualEntrepreneur,
                    loanAmount = Constants.LOAN_AMOUNT_INCOME_IP + 0.1f,
                )
            )
        )
    }

    @Test
    fun testRejectedRequestedAmountBiggerThanAllowedRatingMinus1() {
        assertIs<LoanEngine.LoanResult.Rejected>(
            value = LoanEngineImplementation.process(
                request = testLoanRequest(
                    incomeSource = IncomeSource.IndividualEntrepreneur,
                    rating = -1,
                    loanAmount = Constants.LOAN_AMOUNT_RATING_MINUS_ONE + 0.1f,
                )
            )
        )
    }

    @Test
    fun testRejectedRequestedAmountBiggerThanAllowedRating0() {
        assertIs<LoanEngine.LoanResult.Rejected>(
            value = LoanEngineImplementation.process(
                request = testLoanRequest(
                    incomeSource = IncomeSource.IndividualEntrepreneur,
                    rating = 0,
                    loanAmount = Constants.LOAN_AMOUNT_RATING_ZERO + 0.1f,
                )
            )
        )
    }

    @Test
    fun testRejectedRequestedAmountBiggerThanAllowedRating1() {
        assertIs<LoanEngine.LoanResult.Rejected>(
            value = LoanEngineImplementation.process(
                request = testLoanRequest(
                    incomeSource = IncomeSource.IndividualEntrepreneur,
                    rating = 1,
                    loanAmount = Constants.LOAN_AMOUNT_RATING_ONE + 0.1f,
                )
            )
        )
    }

    @Test
    fun testRejectedRequestedAmountBiggerThanAllowedRating2() {
        assertIs<LoanEngine.LoanResult.Rejected>(
            value = LoanEngineImplementation.process(
                request = testLoanRequest(
                    incomeSource = IncomeSource.IndividualEntrepreneur,
                    rating = 2,
                    loanAmount = Constants.LOAN_AMOUNT_RATING_TWO + 0.1f,
                )
            )
        )
    }

    @Test
    fun testRejectedRatingTooLow() {
        assertIs<LoanEngine.LoanResult.Rejected>(
            value = LoanEngineImplementation.process(
                request = testLoanRequest(
                    incomeSource = IncomeSource.IndividualEntrepreneur,
                    rating = -2,
                    loanAmount = 1f,
                )
            )
        )
    }

    @Test
    fun testRejectedAgeTooLow() {
        assertIs<LoanEngine.LoanResult.Rejected>(
            value = LoanEngineImplementation.process(
                request = testLoanRequest(
                    age = Constants.AGE_MIN - 1,
                    incomeSource = IncomeSource.IndividualEntrepreneur,
                    lastYearIncome = 1,
                    rating = 0,
                    loanAmount = 1f,
                    loanDuration = 5,
                )
            )
        )
    }

    @Test
    fun testApprovedAgeMin() {
        assertIs<LoanEngine.LoanResult.Approved>(
            value = LoanEngineImplementation.process(
                request = testLoanRequest(
                    age = Constants.AGE_MIN,
                    incomeSource = IncomeSource.IndividualEntrepreneur,
                    lastYearIncome = 1,
                    rating = 0,
                    loanAmount = 1f,
                    loanDuration = 5,
                )
            )
        )
    }

    @Test
    fun testApprovedAgeMaxMale() {
        assertIs<LoanEngine.LoanResult.Approved>(
            value = LoanEngineImplementation.process(
                request = testLoanRequest(
                    age = 64,
                    gender = Gender.Male,
                    incomeSource = IncomeSource.IndividualEntrepreneur,
                    lastYearIncome = 10,
                    rating = 0,
                    loanAmount = 1f,
                    loanDuration = 1,
                )
            )
        )
    }

    @Test
    fun testApprovedAgeMaxFemale() {
        assertIs<LoanEngine.LoanResult.Approved>(
            value = LoanEngineImplementation.process(
                request = testLoanRequest(
                    age = 59,
                    gender = Gender.Female,
                    incomeSource = IncomeSource.IndividualEntrepreneur,
                    lastYearIncome = 10,
                    rating = 0,
                    loanAmount = 1f,
                    loanDuration = 1,
                )
            )
        )
    }

    @Test
    fun testRejectedAgeMaxMale() {
        assertIs<LoanEngine.LoanResult.Rejected>(
            value = LoanEngineImplementation.process(
                request = testLoanRequest(
                    age = 65,
                    gender = Gender.Male,
                    incomeSource = IncomeSource.IndividualEntrepreneur,
                    lastYearIncome = 10,
                    rating = 0,
                    loanAmount = 1f,
                    loanDuration = 1,
                )
            )
        )
    }

    @Test
    fun testRejectedAgeMaxFemale() {
        assertIs<LoanEngine.LoanResult.Rejected>(
            value = LoanEngineImplementation.process(
                request = testLoanRequest(
                    age = 60,
                    gender = Gender.Female,
                    incomeSource = IncomeSource.IndividualEntrepreneur,
                    lastYearIncome = 10,
                    rating = 0,
                    loanAmount = 1f,
                    loanDuration = 1,
                )
            )
        )
    }

    @Test
    fun testApprovedLastYearIncome() {
        assertIs<LoanEngine.LoanResult.Approved>(
            value = LoanEngineImplementation.process(
                request = testLoanRequest(
                    age = 20,
                    incomeSource = IncomeSource.IndividualEntrepreneur,
                    lastYearIncome = 3,
                    rating = 0,
                    loanAmount = 1f,
                    loanDuration = 1,
                )
            )
        )
    }

    @Test
    fun testRejectedLastYearIncome() {
        assertIs<LoanEngine.LoanResult.Rejected>(
            value = LoanEngineImplementation.process(
                request = testLoanRequest(
                    age = 20,
                    incomeSource = IncomeSource.IndividualEntrepreneur,
                    lastYearIncome = 1,
                    rating = 0,
                    loanAmount = 1f,
                    loanDuration = 1,
                )
            )
        )
    }

    @Test
    fun testRejectedLoanDuration() {
        assertIs<LoanEngine.LoanResult.Rejected>(
            value = LoanEngineImplementation.process(
                request = testLoanRequest(
                    age = 20,
                    incomeSource = IncomeSource.IndividualEntrepreneur,
                    lastYearIncome = 1,
                    rating = 0,
                    loanAmount = 1f,
                    loanDuration = 0,
                )
            )
        )
    }

    @Test
    fun testRejectedLoanDuration2() {
        assertIs<LoanEngine.LoanResult.Rejected>(
            value = LoanEngineImplementation.process(
                request = testLoanRequest(
                    age = 20,
                    incomeSource = IncomeSource.IndividualEntrepreneur,
                    lastYearIncome = 1,
                    rating = 0,
                    loanAmount = 1f,
                    loanDuration = 100,
                )
            )
        )
    }

    @Test
    fun testRejectedJobless() {
        assertIs<LoanEngine.LoanResult.Rejected>(
            value = LoanEngineImplementation.process(
                request = testLoanRequest(
                    age = 20,
                    incomeSource = IncomeSource.Jobless,
                    lastYearIncome = 3,
                    rating = 0,
                    loanAmount = 1f,
                    loanDuration = 1,
                )
            )
        )
    }
}
