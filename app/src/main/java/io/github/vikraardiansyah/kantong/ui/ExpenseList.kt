package io.github.vikraardiansyah.kantong.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.vikraardiansyah.kantong.Expense
import io.github.vikraardiansyah.kantong.ui.theme.KantongTheme

@Composable
fun ExpenseList(expenses: List<Expense>, onDelete: (Int) -> Unit, modifier: Modifier = Modifier) {
    if (expenses.isEmpty()) {
        Text("No expenses yet", modifier = modifier.padding(16.dp))
    } else {
        LazyColumn(modifier = modifier) {
            items(expenses, key = { it.id }) { expense ->
                ExpenseItem(expense, onDelete = { onDelete(expense.id) })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ExpenseListPreview() {
    val sample = listOf(
        Expense(1, "Nasi goreng", "Food", 25_000),
        Expense(2, "Nasi uduk", "Food", 10_000),
        Expense(3, "Nasi padang", "Food", 35_000),
        Expense(4, "Gojek", "Transport", 15_000),
        Expense(5, "Train", "Transport", 5_000),
    )
    KantongTheme {
        ExpenseList(sample, {})
    }
}

@Preview(showBackground = true)
@Composable
private fun ExpenseListEmptyPreview() {
    KantongTheme {
        ExpenseList(emptyList(), {})
    }
}
