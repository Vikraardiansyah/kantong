package io.github.vikraardiansyah.kantong.ui

import androidx.lifecycle.ViewModel
import io.github.vikraardiansyah.kantong.Expense
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class KantongUiState(val expenses: List<Expense> = emptyList())

class KantongViewModel : ViewModel() {
    private val _state = MutableStateFlow(KantongUiState())
    val state: StateFlow<KantongUiState> = _state.asStateFlow()

    private var nextId = 0

    fun add(name: String, category: String, amount: Long) {
        val isValid = name.isNotBlank() && category.isNotBlank() && amount > 0
        if (isValid) {
            _state.update { s -> s.copy(expenses = s.expenses + Expense(nextId, name, category, amount)) }
            nextId++
        }
    }

    fun delete(id: Int) {
        _state.update { s -> s.copy(expenses = s.expenses.filter { it.id != id }) }
    }
}