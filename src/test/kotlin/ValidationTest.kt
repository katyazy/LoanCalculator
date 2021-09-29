import org.junit.jupiter.api.Test

class ValidationTest {

    @Test
    fun testAgeMinMinus1() {
        assertValidation(
            request = testLoanRequest(age = Constants.AGE_MIN - 1),
            expected = false,
        )
    }

    @Test
    fun testAgeMin() {
        assertValidation(
            request = testLoanRequest(age = Constants.AGE_MIN),
            expected = true,
        )
    }

    @Test
    fun testAgeMax() {
        assertValidation(
            request = testLoanRequest(age = Constants.AGE_MAX),
            expected = true,
        )
    }

    @Test
    fun testAgeMaxPlus1() {
        assertValidation(
            request = testLoanRequest(age = Constants.AGE_MAX + 1),
            expected = false,
        )
    }

    @Test
    fun testRatingSmallerThanMin() {
        assertValidation(
            request = testLoanRequest(rating = Constants.RATING_MIN - 1),
            expected = false,
        )
    }

    @Test
    fun testRatingMin() {
        assertValidation(
            request = testLoanRequest(rating = Constants.RATING_MIN),
            expected = true,
        )
    }

    @Test
    fun testRatingMax() {
        assertValidation(
            request = testLoanRequest(rating = Constants.RATING_MAX),
            expected = true,
        )
    }

    @Test
    fun testRatingBiggerThanMax() {
        assertValidation(
            request = testLoanRequest(rating = Constants.RATING_MAX + 1),
            expected = false,
        )
    }

    @Test
    fun testLoanAmountTooSmall() {
        assertValidation(
            request = testLoanRequest(loanAmount = Constants.LOAN_AMOUNT_MIN - 0.1f),
            expected = false,
        )
    }

    @Test
    fun testLoanAmountSmall() {
        assertValidation(
            request = testLoanRequest(loanAmount = Constants.LOAN_AMOUNT_MIN),
            expected = true,
        )
    }

    @Test
    fun testLoanAmountBig() {
        assertValidation(
            request = testLoanRequest(loanAmount = Constants.LOAN_AMOUNT_MAX),
            expected = true,
        )
    }

    @Test
    fun testLoanAmountTooBig() {
        assertValidation(
            request = testLoanRequest(loanAmount = Constants.LOAN_AMOUNT_MAX + 0.1f),
            expected = false,
        )
    }

    @Test
    fun testLoanDurationTooSmall() {
        assertValidation(
            request = testLoanRequest(loanDuration = Constants.LOAN_DURATION_MIN - 1),
            expected = false,
        )
    }

    @Test
    fun testLoanDurationMin() {
        assertValidation(
            request = testLoanRequest(loanDuration = Constants.LOAN_DURATION_MIN),
            expected = true,
        )
    }

    @Test
    fun testLoanDurationMax() {
        assertValidation(
            request = testLoanRequest(loanDuration = Constants.LOAN_DURATION_MAX),
            expected = true,
        )
    }

    @Test
    fun testLoanDurationTooBig() {
        assertValidation(
            request = testLoanRequest(loanDuration = Constants.LOAN_DURATION_MAX + 1),
            expected = false,
        )
    }
}
