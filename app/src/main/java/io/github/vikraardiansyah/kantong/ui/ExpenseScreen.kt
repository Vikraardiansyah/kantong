package io.github.vikraardiansyah.kantong.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.vikraardiansyah.kantong.Expense
import io.github.vikraardiansyah.kantong.ui.theme.KantongTheme

@Composable
fun ExpenseScreen(modifier: Modifier = Modifier) {
    var expenses by remember { mutableStateOf(emptyList<Expense>()) }
    var nextId by remember { mutableIntStateOf(0) }

    Column(modifier = modifier) {
        AddExpenseForm(onSave = { name, category, amount ->
            expenses = expenses + Expense(nextId, name, category, amount)
            nextId++
        }, modifier = Modifier.padding(horizontal = 16.dp))
        ExpenseList(
            expenses = expenses,
            onDelete = { id -> expenses = expenses.filter { it.id != id } }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ExpenseScreenPreview() {
    KantongTheme { ExpenseScreen() }
}
