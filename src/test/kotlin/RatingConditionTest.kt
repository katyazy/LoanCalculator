import org.junit.jupiter.api.Test

class RatingConditionTest {

    @Test
    fun testRating_minus2() {
        assertCondition<LoanCondition.Result.Rejected>(
            condition = LoanCondition.RatingCondition,
            request = testLoanRequest(rating = -2)
        )
    }

    @Test
    fun testRating_minus1() {
        assertCondition<LoanCondition.Result.Approved>(
            condition = LoanCondition.RatingCondition,
            request = testLoanRequest(rating = -1)
        )
    }

    @Test
    fun testRating_0() {
        assertCondition<LoanCondition.Result.Approved>(
            condition = LoanCondition.RatingCondition,
            request = testLoanRequest(rating = 0)
        )
    }

    @Test
    fun testRating_1() {
        assertCondition<LoanCondition.Result.Approved>(
            condition = LoanCondition.RatingCondition,
            request = testLoanRequest(rating = 1)
        )
    }

    @Test
    fun testRating_2() {
        assertCondition<LoanCondition.Result.Approved>(
            condition = LoanCondition.RatingCondition,
            request = testLoanRequest(rating = 2)
        )
    }
}
