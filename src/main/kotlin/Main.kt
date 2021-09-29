import kotlin.math.log10

fun main() {
    val request = LoanRequest(
        age = 20,
        gender = Gender.Female,
        incomeSource = IncomeSource.Employee,
        lastYearIncome = 5,
        rating = 2,
        loanAmount = 1f,
        loanDuration = 3,
        loanPurpose = Purpose.CarLoan,
    )
    val result = requestLoan(request = request)
    displayResults(request, result)
}

fun requestLoan(
    loanEngine: LoanEngine = LoanEngineImplementation,
    request: LoanRequest,
): LoanEngine.LoanResult = loanEngine.process(request)

object Constants {

    const val AGE_MIN = 18
    const val AGE_MAX = 100

    const val RETIREMENT_AGE_MALE = 65
    const val RETIREMENT_AGE_FEMALE = 60

    const val RATING_MIN = -2
    const val RATING_MAX = 2

    const val LOAN_AMOUNT_MIN = 0.1f
    const val LOAN_AMOUNT_MAX = 10f

    const val LOAN_DURATION_MIN = 1
    const val LOAN_DURATION_MAX = 20

    const val MSG_INVALID_INPUT = "Invalid input"
    const val MSG_INVALID_LOAN_AMOUNT = "Requested loan amount is to high"
    const val MSG_INVALID_PERSON_AGE = "Age is to high."
    const val MSG_YEAR_INCOME_TOO_LOW = "Year income is too low."
    const val MSG_YEAR_ANNUAL_PAYMENT_IS_TO_HIGH = "Annual payment is to high."
    const val MSG_RATING_TO_LOW = "Rating is too low."
    const val MSG_INVALID_JOB = "No job - no loan."

    const val LOAN_AMOUNT_INCOME_PASSIVE = 1f
    const val LOAN_AMOUNT_INCOME_EMPLOYEE = 5f
    const val LOAN_AMOUNT_INCOME_IP = 10f

    const val LOAN_AMOUNT_RATING_MINUS_ONE = 1f
    const val LOAN_AMOUNT_RATING_ZERO = 5f
    const val LOAN_AMOUNT_RATING_ONE = 10f
    const val LOAN_AMOUNT_RATING_TWO = 10f

    const val LOAN_AMOUNT_OTHER = 0f
}

enum class Gender {
    Male, Female
}

enum class IncomeSource {
    Passive, Employee, IndividualEntrepreneur, Jobless
}

enum class Purpose {
    Mortgage, BusinessDevelopment, CarLoan, Consumer
}

data class LoanRequest(
    val age: Int,
    val gender: Gender,
    val incomeSource: IncomeSource,
    val lastYearIncome: Int,
    val rating: Int,

    val loanAmount: Float,
    val loanDuration: Int,
    val loanPurpose: Purpose,
)

interface LoanEngine {

    fun process(request: LoanRequest): LoanResult {
        if (!validate(request)) return LoanResult.Rejected(Constants.MSG_INVALID_INPUT)

        val rejections = verify(request).filterIsInstance<LoanCondition.Result.Rejected>()

        return if (rejections.isEmpty()) {
            val amount = calculateAmount(request)
            if (amount <= 0) {
                return LoanResult.Rejected(Constants.MSG_INVALID_LOAN_AMOUNT)
            }

            val rate = calculateRates(request)
            val annualPayment = annualPayment(request, rate)
            LoanResult.Approved(
                amount = amount,
                rate = rate,
                annualPayment = annualPayment,
            )
        } else {
            LoanResult.Rejected(reasons = rejections.map { it.reason })
        }
    }

    // Годовой платеж по кредиту определяется по следующей формуле:
    // (<сумма кредита> * (1 + <срок погашения> * (<базовая ставка> + <модификаторы>))) / <срок погашения>
    fun annualPayment(request: LoanRequest, rate: Float): Float =
        request.loanAmount * (1 + request.loanDuration * rate) / request.loanDuration

    fun validate(request: LoanRequest): Boolean {
        if (request.age < Constants.AGE_MIN || request.age > Constants.AGE_MAX) return false
        if (request.rating !in Constants.RATING_MIN..Constants.RATING_MAX) return false
        if (request.loanAmount < Constants.LOAN_AMOUNT_MIN || request.loanAmount > Constants.LOAN_AMOUNT_MAX) return false
        if (request.loanDuration !in Constants.LOAN_DURATION_MIN..Constants.LOAN_DURATION_MAX) return false

        return true
    }

    fun verify(request: LoanRequest): List<LoanCondition.Result>

    fun calculateAmount(request: LoanRequest): Float

    fun calculateRates(request: LoanRequest): Float

    sealed interface LoanResult {

        data class Rejected(
            val reasons: List<String>,
        ) : LoanResult {
            constructor(reason: String) : this(listOf(reason))
        }

        data class Approved(
            val amount: Float,
            val rate: Float,
            val annualPayment: Float,
        ) : LoanResult
    }
}

object LoanEngineImplementation : LoanEngine {

    private val conditions = listOf(
        LoanCondition.RetirementAgeCondition,
        LoanCondition.LoanAmountCondition,
        LoanCondition.RatingCondition,
        LoanCondition.IncomeSourceCondition,
        LoanCondition.AnnualPaymentCondition,
    )

    private val amountCalculators = listOf(
        AmountCalculator.IncomeSourceCalculator,
        AmountCalculator.RatingCalculator,
    )

    private val rateModifiers = listOf(
        RatesModifier.PurposeModifier,
        RatesModifier.RatingModifier,
        RatesModifier.AmountModifier,
        RatesModifier.IncomeSourceModifier,
    )

    override fun verify(request: LoanRequest): List<LoanCondition.Result.Rejected> =
        conditions
            .map { it.test(engine = this, request = request) }
            .filterIsInstance<LoanCondition.Result.Rejected>()

    override fun calculateAmount(request: LoanRequest): Float {
        val maxAllowed = amountCalculators
            .map { it.calculate(engine = this, request = request) }
            .minOrNull() ?: 0f
        return if (maxAllowed >= request.loanAmount) {
            request.loanAmount
        } else {
            0f
        }
    }

    override fun calculateRates(request: LoanRequest): Float =
        rateModifiers.fold(1000) { rates, modifier ->
            modifier.modify(this, request, rates)
        } / 10_000f
}

sealed interface LoanCondition {

    fun test(engine: LoanEngine, request: LoanRequest): Result

    sealed interface Result {

        data class Rejected(val reason: String) : Result

        object Approved : Result
    }

    //Если возраст превышает пенсионный возраст на момент возврата кредита --> кредит не выдаётся
    object RetirementAgeCondition : LoanCondition {

        override fun test(engine: LoanEngine, request: LoanRequest): Result {
            val clientAge = request.age + request.loanDuration
            val retirementAge = when (request.gender) {
                Gender.Male -> Constants.RETIREMENT_AGE_MALE
                Gender.Female -> Constants.RETIREMENT_AGE_FEMALE
            }
            return if (clientAge > retirementAge) {
                Result.Rejected(Constants.MSG_INVALID_PERSON_AGE)
            } else {
                Result.Approved
            }
        }
    }

    //Если результат деления запрошенной суммы на срок погашения в годах более трети годового дохода --> кредит не выдаётся
    object LoanAmountCondition : LoanCondition {

        override fun test(engine: LoanEngine, request: LoanRequest): Result {
            val annualLoan = request.loanAmount / request.loanDuration
            val annualIncome = request.lastYearIncome / 3f
            return if (annualLoan > annualIncome) {
                Result.Rejected(Constants.MSG_YEAR_INCOME_TOO_LOW)
            } else {
                Result.Approved
            }
        }
    }

    //Если кредитный рейтинг -2 --> кредит не выдаётся
    object RatingCondition : LoanCondition {

        override fun test(engine: LoanEngine, request: LoanRequest): Result =
            if (request.rating <= Constants.RATING_MIN) {
                Result.Rejected(Constants.MSG_RATING_TO_LOW)
            } else {
                Result.Approved
            }
    }

    //Если в источнике дохода указано "безработный" --> кредит не выдаётся
    object IncomeSourceCondition : LoanCondition {

        override fun test(engine: LoanEngine, request: LoanRequest): Result =
            if (request.incomeSource == IncomeSource.Jobless) {
                Result.Rejected(Constants.MSG_INVALID_JOB)
            } else {
                Result.Approved
            }
    }

    //Если годовой платёж (включая проценты) больше половины дохода --> кредит не выдаётся
    object AnnualPaymentCondition : LoanCondition {

        override fun test(engine: LoanEngine, request: LoanRequest): Result {
            val annualPayment = engine.annualPayment(request, engine.calculateRates(request))
            return if (annualPayment > request.lastYearIncome / 2f) {
                Result.Rejected(Constants.MSG_YEAR_ANNUAL_PAYMENT_IS_TO_HIGH)
            } else {
                Result.Approved
            }
        }
    }
}

// Если работают несколько условий по сумме кредита - выбирается наименьшая
sealed interface AmountCalculator {

    fun calculate(engine: LoanEngine, request: LoanRequest): Float

    // При пассивном доходе выдаётся кредит на сумму до 1 млн, наёмным работникам - до 5 млн, собственное дело - до 10 млн
    object IncomeSourceCalculator : AmountCalculator {

        override fun calculate(engine: LoanEngine, request: LoanRequest): Float =
            when (request.incomeSource) {
                IncomeSource.Passive -> Constants.LOAN_AMOUNT_INCOME_PASSIVE
                IncomeSource.Employee -> Constants.LOAN_AMOUNT_INCOME_EMPLOYEE
                IncomeSource.IndividualEntrepreneur -> Constants.LOAN_AMOUNT_INCOME_IP
                IncomeSource.Jobless -> Constants.LOAN_AMOUNT_OTHER
            }
    }

    // При кредитном рейтинге -1 выдаётся кредит на сумму до 1 млн, при 0 - до 5 млн, при 1 или 2 - до 10 млн
    object RatingCalculator : AmountCalculator {

        override fun calculate(engine: LoanEngine, request: LoanRequest): Float =
            when (request.rating) {
                -1 -> Constants.LOAN_AMOUNT_RATING_MINUS_ONE
                0 -> Constants.LOAN_AMOUNT_RATING_ZERO
                1 -> Constants.LOAN_AMOUNT_RATING_ONE
                2 -> Constants.LOAN_AMOUNT_RATING_TWO
                else -> Constants.LOAN_AMOUNT_OTHER
            }
    }
}

// Все модификаторы процентной ставки суммируются, применяется итоговый модификатор
sealed interface RatesModifier {

    fun modify(engine: LoanEngine, request: LoanRequest, rate: Int): Int

    // -2% для ипотеки, -0.5% для развития бизнеса, +1.5% для потребительского кредита
    object PurposeModifier : RatesModifier {

        override fun modify(engine: LoanEngine, request: LoanRequest, rate: Int): Int =
            rate + when (request.loanPurpose) {
                Purpose.Mortgage -> -200
                Purpose.BusinessDevelopment -> -50
                Purpose.CarLoan -> 0
                Purpose.Consumer -> 150
            }
    }

    // +1.5% для кредитного рейтинга -1, 0% для кредитного рейтинга 0, -0.25% для кредитного рейтинга 1, -0.75% для кредитного рейтинга 2
    object RatingModifier : RatesModifier {

        override fun modify(engine: LoanEngine, request: LoanRequest, rate: Int): Int =
            rate + when (request.rating) {
                -1 -> 150
                0 -> 0
                1 -> -25
                2 -> -75
                else -> 0
            }
    }

    // Модификатор в зависимости от запрошенной суммы рассчитывается по формуле [-log(sum)];
    // например, для 0.1 млн изменение ставки составит +1%,
    // для 1 млн - 0%,
    // для 10 млн изменение ставки составит -1%
    object AmountModifier : RatesModifier {

        override fun modify(engine: LoanEngine, request: LoanRequest, rate: Int): Int =
            rate - (log10(request.loanAmount) * 100f).toInt()
    }

    // Для пассивного дохода ставка повышается на 0.5%,
    // для наемных работников ставка снижается на 0.25%,
    // для заемщиков с собственным бизнесом ставка повышается на 0.25%
    object IncomeSourceModifier : RatesModifier {
        override fun modify(engine: LoanEngine, request: LoanRequest, rate: Int): Int =
            rate + when (request.incomeSource) {
                IncomeSource.Passive -> 50
                IncomeSource.Employee -> -25
                IncomeSource.IndividualEntrepreneur -> 25
                IncomeSource.Jobless -> 0
            }
    }
}
