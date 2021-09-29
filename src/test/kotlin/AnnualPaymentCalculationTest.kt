import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AnnualPaymentCalculationTest {

    @Test
    fun test_0point1Million_1Year_10Percent() {
        assertEquals(
            expected = 0.11f,
            actual = LoanEngineImplementation.annualPayment(
                request = testLoanRequest(
                    loanAmount = 0.1f,
                    loanDuration = 1,
                ),
                rate = 0.1f,
            ),
            absoluteTolerance = 0.001f,
        )
    }

    @Test
    fun test_5Million_1Year_10Percent() {
        assertEquals(
            expected = 5.5f,
            actual = LoanEngineImplementation.annualPayment(
                request = testLoanRequest(
                    loanAmount = 5f,
                    loanDuration = 1,
                ),
                rate = 0.1f,
            ),
            absoluteTolerance = 0.001f,
        )
    }

    @Test
    fun test_10Million_1Year_10Percent() {
        assertEquals(
            expected = 11f,
            actual = LoanEngineImplementation.annualPayment(
                request = testLoanRequest(
                    loanAmount = 10f,
                    loanDuration = 1,
                ),
                rate = 0.1f,
            ),
            absoluteTolerance = 0.001f,
        )
    }

    @Test
    fun test_1Million_1Year_10Percent() {
        assertEquals(
            expected = 1.1f,
            actual = LoanEngineImplementation.annualPayment(
                request = testLoanRequest(
                    loanAmount = 1f,
                    loanDuration = 1,
                ),
                rate = 0.1f,
            ),
            absoluteTolerance = 0.001f,
        )
    }

    @Test
    fun test_1Million_10Years_10Percent() {
        assertEquals(
            expected = 0.2f,
            actual = LoanEngineImplementation.annualPayment(
                request = testLoanRequest(
                    loanAmount = 1f,
                    loanDuration = 10,
                ),
                rate = 0.1f,
            ),
            absoluteTolerance = 0.001f,
        )
    }

    @Test
    fun test_1Million_20Years_10Percent() {
        assertEquals(
            expected = 0.15f,
            actual = LoanEngineImplementation.annualPayment(
                request = testLoanRequest(
                    loanAmount = 1f,
                    loanDuration = 20,
                ),
                rate = 0.1f,
            ),
            absoluteTolerance = 0.001f,
        )
    }

    @Test
    fun test_1Million_20Years_1Percent() {
        assertEquals(
            expected = 0.06f,
            actual = LoanEngineImplementation.annualPayment(
                request = testLoanRequest(
                    loanAmount = 1f,
                    loanDuration = 20,
                ),
                rate = 0.01f,
            ),
            absoluteTolerance = 0.001f,
        )
    }

    @Test
    fun test_1Million_20Years_5Percent() {
        assertEquals(
            expected = 0.1f,
            actual = LoanEngineImplementation.annualPayment(
                request = testLoanRequest(
                    loanAmount = 1f,
                    loanDuration = 20,
                ),
                rate = 0.05f,
            ),
            absoluteTolerance = 0.001f,
        )
    }

    @Test
    fun test_1Million_20Years_10point5Percent() {
        assertEquals(
            expected = 0.155f,
            actual = LoanEngineImplementation.annualPayment(
                request = testLoanRequest(
                    loanAmount = 1f,
                    loanDuration = 20,
                ),
                rate = 0.105f,
            ),
            absoluteTolerance = 0.001f,
        )
    }

    @Test
    fun test_1Million_20Years_15point5Percent() {
        assertEquals(
            expected = 0.205f,
            actual = LoanEngineImplementation.annualPayment(
                request = testLoanRequest(
                    loanAmount = 1f,
                    loanDuration = 20,
                ),
                rate = 0.155f,
            ),
            absoluteTolerance = 0.0155f,
        )
    }
}
