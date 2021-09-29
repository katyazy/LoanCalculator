import org.junit.jupiter.api.Test

class AmountTest {

    @Test
    fun test1() {
        assertAmount(
            request = testLoanRequest(incomeSource = IncomeSource.Passive, rating = -1, loanAmount = 1.1f),
            expectedAmount = 0f,
        )
    }

    @Test
    fun test2() {
        assertAmount(
            request = testLoanRequest(incomeSource = IncomeSource.Passive, rating = 0, loanAmount = 1f),
            expectedAmount = 1f,
        )
    }

    @Test
    fun test3() {
        assertAmount(
            request = testLoanRequest(incomeSource = IncomeSource.Passive, rating = 1, loanAmount = 0.9f),
            expectedAmount = 0.9f,
        )
    }

    @Test
    fun test4() {
        assertAmount(
            request = testLoanRequest(incomeSource = IncomeSource.Passive, rating = 2, loanAmount = 0.9f),
            expectedAmount = 0.9f,
        )
    }

    @Test
    fun test5() {
        assertAmount(
            request = testLoanRequest(incomeSource = IncomeSource.Passive, rating = 1, loanAmount = 1.9f),
            expectedAmount = 0f,
        )
    }

    @Test
    fun test6() {
        assertAmount(
            request = testLoanRequest(incomeSource = IncomeSource.Employee, rating = 0, loanAmount = 4.9f),
            expectedAmount = 4.9f,
        )
    }

    @Test
    fun test7() {
        assertAmount(
            request = testLoanRequest(incomeSource = IncomeSource.Employee, rating = 1, loanAmount = 5.1f),
            expectedAmount = 0f,
        )
    }

    @Test
    fun test8() {
        assertAmount(
            request = testLoanRequest(incomeSource = IncomeSource.Employee, rating = 2, loanAmount = 10f),
            expectedAmount = 0f,
        )
    }

    @Test
    fun test9() {
        assertAmount(
            request = testLoanRequest(incomeSource = IncomeSource.Employee, rating = -1, loanAmount = 1f),
            expectedAmount = 1f,
        )
    }

    @Test
    fun test10() {
        assertAmount(
            request = testLoanRequest(incomeSource = IncomeSource.IndividualEntrepreneur, rating = 1, loanAmount = 10f),
            expectedAmount = 10f,
        )
    }

    @Test
    fun test11() {
        assertAmount(
            request = testLoanRequest(incomeSource = IncomeSource.IndividualEntrepreneur, rating = -1, loanAmount = 1f),
            expectedAmount = 1f,
        )
    }

    @Test
    fun test12() {
        assertAmount(
            request = testLoanRequest(incomeSource = IncomeSource.IndividualEntrepreneur, rating = 0, loanAmount = 6f),
            expectedAmount = 0f,
        )
    }

    @Test
    fun test13() {
        assertAmount(
            request = testLoanRequest(incomeSource = IncomeSource.IndividualEntrepreneur, rating = 2, loanAmount = 10f),
            expectedAmount = 10f,
        )
    }
}
