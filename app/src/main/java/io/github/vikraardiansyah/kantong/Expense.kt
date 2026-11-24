package io.github.vikraardiansyah.kantong

import java.io.Serializable

data class Expense(val id: Int, val name: String, val category: String, val amount: Long) :
    Serializable
