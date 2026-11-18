package io.github.vikraardiansyah.kantong.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.vikraardiansyah.kantong.Transaksi
import io.github.vikraardiansyah.kantong.toRupiah
import io.github.vikraardiansyah.kantong.ui.theme.KantongTheme

@Composable
fun TransaksiItem(t: Transaksi, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(t.nama, fontWeight = FontWeight.Bold)
            Text(t.kategori, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Text(t.jumlah.toRupiah())
    }
}

@Preview(showBackground = true)
@Composable
private fun TransaksiItemPreview() {
    KantongTheme {
        TransaksiItem(Transaksi(1, "Nasi goreng", "Makan", 25_000))
    }
}