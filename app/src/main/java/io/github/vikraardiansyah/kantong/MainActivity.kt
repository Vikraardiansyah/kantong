package io.github.vikraardiansyah.kantong

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import io.github.vikraardiansyah.kantong.ui.DaftarTransaksi
import io.github.vikraardiansyah.kantong.ui.theme.KantongTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KantongTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DaftarTransaksi(
                        daftar = List(20) { i ->
                            Transaksi(
                                id = i,
                                nama = "item $i",
                                kategori = "category $i",
                                jumlah = (i + 1) * 1000L
                            )
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

