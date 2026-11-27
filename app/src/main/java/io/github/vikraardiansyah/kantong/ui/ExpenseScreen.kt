package io.github.vikraardiansyah.kantong.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.vikraardiansyah.kantong.Expense
import io.github.vikraardiansyah.kantong.ui.theme.KantongTheme

@Composable
fun ExpenseScreen(
    modifier: Modifier = Modifier,
    viewModel: KantongViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ExpenseContent(
        expenses = state.expenses,
        onSave = { name, category, amount -> viewModel.add(name, category, amount) },
        onDelete = { id -> viewModel.delete(id) },
        modifier = modifier,
    )
}

@Composable
fun ExpenseContent(
    expenses: List<Expense>,
    onSave: (name: String, category: String, amount: Long) -> Unit,
    onDelete: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        AddExpenseForm(
            onSave = onSave,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        ExpenseList(
            expenses = expenses,
            onDelete = onDelete
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ExpenseScreenPreview() {
    val expenses = (1..5).map { Expense(it, "item $it", "category $it", it.toLong() * 10000) }
    KantongTheme { ExpenseContent(expenses, { _, _, _ -> }, {}) }
}
