package io.github.vikraardiansyah.kantong.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.vikraardiansyah.kantong.Transaksi
import io.github.vikraardiansyah.kantong.ui.theme.KantongTheme

@Composable
fun DaftarTransaksi(daftar: List<Transaksi>, modifier: Modifier = Modifier) {
    if (daftar.isEmpty()) {
        Text("Belum ada Transaksi", modifier = modifier)
    } else {
        LazyColumn(modifier = modifier) {
            items(daftar, key = { it.id }) { t ->
                TransaksiItem(t)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DaftarTransaksiPreview() {
    val contoh = listOf(
        Transaksi(1, "Nasi goreng", "Makan", 25_000),
        Transaksi(2, "Nasi Uduk", "Makan", 10_000),
        Transaksi(3, "Nasi Padang", "Makan", 35_000),
        Transaksi(4, "Gojek", "Transport", 15_000),
        Transaksi(5, "Kereta", "Transport", 5_000),
    )
    KantongTheme {
        DaftarTransaksi(contoh)
    }
}

@Preview(showBackground = true)
@Composable
private fun DaftarTransaksiKosongPreview() {
    val contoh: List<Transaksi> = emptyList()
    KantongTheme {
        DaftarTransaksi(contoh)
    }
}