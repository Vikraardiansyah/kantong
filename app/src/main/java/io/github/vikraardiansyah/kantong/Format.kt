package io.github.vikraardiansyah.kantong

fun Long.toRupiah(): String = "Rp${
    toString()
        .reversed()
        .chunked(3)
        .joinToString(".")
        .reversed()
}"