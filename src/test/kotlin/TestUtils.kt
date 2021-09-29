import kotlin.random.Random
import kotlin.test.assertEquals
import kotlin.test.assertIs

fun testLoanRequest(
    age: Int = Random.nextInt(Constants.AGE_MIN, Constants.AGE_MAX + 1),
    gender: Gender = Gender.values().random(),
    incomeSource: IncomeSource = IncomeSource.values().random(),
    lastYearIncome: Int = Random.nextInt(1, 11),
    rating: Int = Random.nextInt(-2, 3),
    loanAmount: Float = Random.nextInt(
        from = (Constants.LOAN_AMOUNT_MIN * 10).toInt(),
        until = (Constants.LOAN_AMOUNT_MAX * 10).toInt() + 1) / 10f,
    loanDuration: Int = Random.nextInt(
        from = Constants.LOAN_DURATION_MIN,
        until = Constants.LOAN_DURATION_MAX + 1,
    ),
    loanPurpose: Purpose = Purpose.values().random(),
): LoanRequest = LoanRequest(
    age = age,
    gender = gender,
    incomeSource = incomeSource,
    lastYearIncome = lastYearIncome,
    rating = rating,
    loanAmount = loanAmount,
    loanDuration = loanDuration,
    loanPurpose = loanPurpose,
)

inline fun <reified T : LoanCondition.Result> assertCondition(
    condition: LoanCondition,
    request: LoanRequest,
) {
    val result = condition.test(engine = LoanEngineImplementation, request = request)
    assertIs<T>(
        value = result,
        message = "Unexpected result: $result, expected to be of type ${T::class.simpleName}",
    )
}

fun assertValidation(
    request: LoanRequest,
    expected: Boolean,
) {
    assertEquals(
        expected = expected,
        actual = LoanEngineImplementation.validate(request),
        message = "Expected to be ${if (expected) "valid" else "invalid"}, request: $request"
    )
}

fun assertAmountCalculator(
    calculator: AmountCalculator,
    request: LoanRequest,
    expectedAmount: Float,
) {
    assertEquals(
        expected = expectedAmount,
        actual = calculator.calculate(LoanEngineImplementation, request),
        message = "Expected to be $expectedAmount, request: $request"
    )
}

fun assertAmount(
    request: LoanRequest,
    expectedAmount: Float,
) {
    assertEquals(
        expected = expectedAmount,
        actual = LoanEngineImplementation.calculateAmount(request),
        message = "Expected to be $expectedAmount, request: $request"
    )
}

fun assertRatesModifier(
    modifier: RatesModifier,
    request: LoanRequest,
    expectedModifier: Float,
) {
    assertEquals(
        expected = expectedModifier,
        actual = modifier.modify(LoanEngineImplementation, request, 0) / 10_000f,
        message = "Expected to be $expectedModifier, request: $request"
    )
}

fun assertRates(
    request: LoanRequest,
    expectedRate: Float,
) {
    assertEquals(
        expected = expectedRate,
        actual = LoanEngineImplementation.calculateRates(request),
        message = "Expected to be $expectedRate, request: $request"
    )
}
