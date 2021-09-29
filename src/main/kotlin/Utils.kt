fun displayResults(request: LoanRequest, result: LoanEngine.LoanResult) {
    println("Request: \n\t- $request")
    when (result) {
        is LoanEngine.LoanResult.Rejected -> {
            println("Rejected: \n\t${result.reasons.joinToString(separator = "\n\t") { "- $it" }}")
        }

        is LoanEngine.LoanResult.Approved -> {
            println("Approved: \n\tAmount=${result.amount} at ${result.rate.pretty}% rate, annual payment = ${result.annualPayment}")
        }
    }
    println()
}

private val Float.pretty: String
    get() = String.format("%.2f", this * 100)
