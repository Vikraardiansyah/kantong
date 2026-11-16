package com.example.latihan.kantong

data class Transaksi(val id: Int, val nama: String, val kategori: String, val jumlah: Long)

sealed interface HasilTambah {
    data class Berhasil(val transaksi: Transaksi) : HasilTambah
    data class Gagal(val alasan: String) : HasilTambah
}

fun formatRupiah(jumlah: Long): String =
    "Rp${jumlah.toString().reversed().chunked(3).joinToString(".").reversed()}"

class Buku {
    private var nextId = 1
    var daftar: List<Transaksi> = emptyList()
        private set

    fun tambah(nama: String, kategori: String, jumlahTeks: String): HasilTambah {
        if (nama.isBlank()) return HasilTambah.Gagal("nama tidak boleh kosong")
        val jumlah = jumlahTeks.toLongOrNull()
            ?: return HasilTambah.Gagal("jumlah harus angka, contoh 25000")
        if (jumlah <= 0) return HasilTambah.Gagal("jumlah harus lebih dari 0")
        val transaksiBaru = Transaksi(nextId, nama, kategori, jumlah)
        daftar = daftar + transaksiBaru
        nextId++
        return HasilTambah.Berhasil(transaksiBaru)
    }

    fun totalPerKategori(): Map<String, Long> =
        daftar
            .groupBy { it.kategori }
            .mapValues { (_, isi) -> isi.sumOf { it.jumlah } }

    fun terbesar(): Transaksi? =
        daftar.maxByOrNull { it.jumlah }
}

fun main() {
    val buku = Buku()
    while (true) {
        println("1) Tambah  2) Daftar  3) Total per kategori  4) Terbesar  0) Keluar")
        print("> ")
        val pilihan = readln()
        when (pilihan) {
            "1" -> {
                print("Nama: ")
                val nama = readln()
                print("Kategori: ")
                val kategori = readln()
                print("Jumlah: ")
                val jumlah = readln()

                val hasil = buku.tambah(nama = nama, kategori = kategori, jumlahTeks = jumlah)
                when (hasil) {
                    is HasilTambah.Berhasil -> println(
                        "Tersimpan: ${hasil.transaksi.nama} (${hasil.transaksi.kategori}) ${
                            formatRupiah(
                                hasil.transaksi.jumlah
                            )
                        }"
                    )

                    is HasilTambah.Gagal -> println("Gagal: ${hasil.alasan}")
                }
            }

            "2" -> {
                val daftar = buku.daftar
                if (daftar.isEmpty()) {
                    println("Belum ada transaksi")
                } else {
                    daftar.forEach { (_, nama, kategori, jumlah) ->
                        println("$nama ($kategori) ${formatRupiah(jumlah)}")
                    }
                }
            }

            "3" -> {
                val totalPerKategori = buku.totalPerKategori()
                if (totalPerKategori.isEmpty()) {
                    println("Belum ada transaksi")
                } else {
                    totalPerKategori.forEach { (kategori, jumlah) ->
                        println("$kategori: ${formatRupiah(jumlah)}")
                    }
                }
            }

            "4" -> {
                val terbesar = buku.terbesar()
                if (terbesar == null) {
                    println("Belum ada transaksi")
                } else {
                    println(
                        "Terbesar: ${terbesar.nama} (${terbesar.kategori}) ${
                            formatRupiah(
                                terbesar.jumlah
                            )
                        }"
                    )
                }

            }

            "0" -> {
                println("Sampai jumpa!")
                break
            }

            else -> println("Pilihan tidak dikenal")
        }
    }
}