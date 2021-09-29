import org.junit.jupiter.api.Test

class RatingCalculatorTest {

    @Test
    fun testRatingMinus1() {
        assertAmountCalculator(
            calculator = AmountCalculator.RatingCalculator,
            request = testLoanRequest(rating = -1),
            expectedAmount = Constants.LOAN_AMOUNT_RATING_MINUS_ONE,
        )
    }

    @Test
    fun testRating0() {
        assertAmountCalculator(
            calculator = AmountCalculator.RatingCalculator,
            request = testLoanRequest(rating = 0),
            expectedAmount = Constants.LOAN_AMOUNT_RATING_ZERO,
        )
    }

    @Test
    fun testRating1() {
        assertAmountCalculator(
            calculator = AmountCalculator.RatingCalculator,
            request = testLoanRequest(rating = 1),
            expectedAmount = Constants.LOAN_AMOUNT_RATING_ONE,
        )
    }

    @Test
    fun testRating2() {
        assertAmountCalculator(
            calculator = AmountCalculator.RatingCalculator,
            request = testLoanRequest(rating = 2),
            expectedAmount = Constants.LOAN_AMOUNT_RATING_TWO,
        )
    }
}
