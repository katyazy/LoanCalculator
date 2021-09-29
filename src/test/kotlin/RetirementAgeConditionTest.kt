import org.junit.jupiter.api.Test

internal class RetirementAgeConditionTest {

    @Test
    fun testRetirementAgeConditionApproved() {
        assertCondition<LoanCondition.Result.Approved>(
            condition = LoanCondition.RetirementAgeCondition,
            request = testLoanRequest(age = 20)
        )
    }

    @Test
    fun testRetirementAgeConditionRejectedMale() {
        assertCondition<LoanCondition.Result.Rejected>(
            condition = LoanCondition.RetirementAgeCondition,
            request = testLoanRequest(age = 65, gender = Gender.Male)
        )
    }

    @Test
    fun testRetirementAgeConditionRejectedFemale() {
        assertCondition<LoanCondition.Result.Rejected>(
            condition = LoanCondition.RetirementAgeCondition,
            request = testLoanRequest(age = 60, gender = Gender.Female)
        )
    }

    @Test
    fun testRetirementAgeConditionApprovedMale_45_20() {
        assertCondition<LoanCondition.Result.Approved>(
            condition = LoanCondition.RetirementAgeCondition,
            request = testLoanRequest(age = 45, gender = Gender.Male, loanDuration = 20)
        )
    }

    @Test
    fun testRetirementAgeConditionApprovedFemale_40_20() {
        assertCondition<LoanCondition.Result.Approved>(
            condition = LoanCondition.RetirementAgeCondition,
            request = testLoanRequest(age = 40, gender = Gender.Female, loanDuration = 20)
        )
    }

    @Test
    fun testRetirementAgeConditionRejectedMale_46_20() {
        assertCondition<LoanCondition.Result.Rejected>(
            condition = LoanCondition.RetirementAgeCondition,
            request = testLoanRequest(age = 46, gender = Gender.Male, loanDuration = 20)
        )
    }

    @Test
    fun testRetirementAgeConditionRejectedFemale_41_20() {
        assertCondition<LoanCondition.Result.Rejected>(
            condition = LoanCondition.RetirementAgeCondition,
            request = testLoanRequest(age = 41, gender = Gender.Female, loanDuration = 20)
        )
    }

    @Test
    fun testRetirementAgeConditionRejectedMale_64_1() {
        assertCondition<LoanCondition.Result.Approved>(
            condition = LoanCondition.RetirementAgeCondition,
            request = testLoanRequest(age = 64, gender = Gender.Male, loanDuration = 1)
        )
    }

    @Test
    fun testRetirementAgeConditionRejectedFemale_59_1() {
        assertCondition<LoanCondition.Result.Approved>(
            condition = LoanCondition.RetirementAgeCondition,
            request = testLoanRequest(age = 59, gender = Gender.Female, loanDuration = 1)
        )
    }
}
