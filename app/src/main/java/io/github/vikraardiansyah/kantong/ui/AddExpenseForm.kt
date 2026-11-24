package io.github.vikraardiansyah.kantong.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import io.github.vikraardiansyah.kantong.ui.theme.KantongTheme

@Composable
fun AddExpenseForm(
    onSave: (name: String, category: String, amount: Long) -> Unit,
    modifier: Modifier = Modifier
) {
    var name by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var amountText by remember { mutableStateOf("") }
    val isAmountInvalid = amountText.isNotBlank() && (amountText.toLongOrNull() ?: 0) <= 0
    val isValid =
        name.isNotBlank() && category.isNotBlank() && amountText.isNotBlank() && !isAmountInvalid

    Column(modifier = modifier) {
        OutlinedTextField(label = { Text("Name") }, value = name, onValueChange = { name = it })
        OutlinedTextField(
            label = { Text("Category") },
            value = category,
            onValueChange = { category = it }
        )
        OutlinedTextField(
            value = amountText,
            onValueChange = { amountText = it },
            label = { Text("Amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = isAmountInvalid
        )
        Button(onClick = {
            amountText.toLongOrNull()?.let {
                onSave(name, category, it)
                name = ""
                category = ""
                amountText = ""
            }
        }, enabled = isValid) {
            Text("Save")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AddExpenseFormPreview() {
    KantongTheme {
        AddExpenseForm(onSave = { _, _, _ -> })
    }
}