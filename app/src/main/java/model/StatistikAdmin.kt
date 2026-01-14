package model

/**
 * Model data untuk Statistik Admin Dashboard
 */
data class StatistikAdmin(
    val totalSiswa: Int,
    val totalHadirHariIni: Int,
    val izinSakit: Int,
    val persentaseKehadiran: Int // dalam persen (0-100)
)