package com.example.latihan.kantong

data class Expense(val id: Int, val name: String, val category: String, val amount: Long)

sealed interface AddResult {
    data class Success(val expense: Expense) : AddResult
    data class Failure(val reason: String) : AddResult
}

fun formatRupiah(amount: Long): String =
    "Rp${amount.toString().reversed().chunked(3).joinToString(".").reversed()}"

class Ledger {
    private var nextId = 1
    var expenses: List<Expense> = emptyList()
        private set

    fun add(name: String, category: String, amountText: String): AddResult {
        if (name.isBlank()) return AddResult.Failure("name must not be empty")
        val amount = amountText.toLongOrNull()
            ?: return AddResult.Failure("amount must be a number, e.g. 25000")
        if (amount <= 0) return AddResult.Failure("amount must be greater than 0")
        val newExpense = Expense(nextId, name, category, amount)
        expenses = expenses + newExpense
        nextId++
        return AddResult.Success(newExpense)
    }

    fun totalByCategory(): Map<String, Long> =
        expenses
            .groupBy { it.category }
            .mapValues { (_, items) -> items.sumOf { it.amount } }

    fun largest(): Expense? =
        expenses.maxByOrNull { it.amount }
}

fun main() {
    val ledger = Ledger()
    while (true) {
        println("1) Add  2) List  3) Total by category  4) Largest  0) Exit")
        print("> ")
        val choice = readln()
        when (choice) {
            "1" -> {
                print("Name: ")
                val name = readln()
                print("Category: ")
                val category = readln()
                print("Amount: ")
                val amount = readln()

                val result = ledger.add(name = name, category = category, amountText = amount)
                when (result) {
                    is AddResult.Success -> println(
                        "Saved: ${result.expense.name} (${result.expense.category}) ${
                            formatRupiah(result.expense.amount)
                        }"
                    )

                    is AddResult.Failure -> println("Failed: ${result.reason}")
                }
            }

            "2" -> {
                val expenses = ledger.expenses
                if (expenses.isEmpty()) {
                    println("No expenses yet")
                } else {
                    expenses.forEach { (_, name, category, amount) ->
                        println("$name ($category) ${formatRupiah(amount)}")
                    }
                }
            }

            "3" -> {
                val totalByCategory = ledger.totalByCategory()
                if (totalByCategory.isEmpty()) {
                    println("No expenses yet")
                } else {
                    totalByCategory.forEach { (category, total) ->
                        println("$category: ${formatRupiah(total)}")
                    }
                }
            }

            "4" -> {
                val largest = ledger.largest()
                if (largest == null) {
                    println("No expenses yet")
                } else {
                    println(
                        "Largest: ${largest.name} (${largest.category}) ${
                            formatRupiah(largest.amount)
                        }"
                    )
                }
            }

            "0" -> {
                println("Bye!")
                break
            }

            else -> println("Unknown option")
        }
    }
}
