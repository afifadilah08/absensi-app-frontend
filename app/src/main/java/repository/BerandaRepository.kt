package com.xirpl2.SASMobile.repository

import com.xirpl2.SASMobile.model.JadwalSholatData
import com.xirpl2.SASMobile.model.RiwayatAbsensiData
import com.xirpl2.SASMobile.model.UserData
import com.xirpl2.SASMobile.model.StatistikData
import com.xirpl2.SASMobile.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository untuk mengelola data Beranda dari API
 * Menggunakan coroutines untuk async operations
 */
class BerandaRepository {
    
    private val apiService = RetrofitClient.apiService
    
    /**
     * Get jadwal sholat dari API
     * @param token Auth token user
     * @return Result dengan data atau error
     */
    suspend fun getJadwalSholat(token: String): Result<List<JadwalSholatData>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getJadwalSholat("Bearer $token")
                if (response.isSuccessful && response.body()?.success == true) {
                    Result.success(response.body()!!.data)
                } else {
                    Result.failure(Exception(response.body()?.message ?: "Gagal mengambil jadwal sholat"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
    
    /**
     * Get riwayat absensi dari API
     */
    suspend fun getRiwayatAbsensi(token: String, limit: Int = 10): Result<List<RiwayatAbsensiData>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getRiwayatAbsensi("Bearer $token", limit)
                if (response.isSuccessful && response.body()?.success == true) {
                    Result.success(response.body()!!.data)
                } else {
                    Result.failure(Exception(response.body()?.message ?: "Gagal mengambil riwayat absensi"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
    
    /**
     * Get user profile (untuk mendapatkan jenis kelamin)
     */
    suspend fun getUserProfile(token: String): Result<UserData> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getUserProfile("Bearer $token")
                if (response.isSuccessful && response.body()?.success == true) {
                    Result.success(response.body()!!.data)
                } else {
                    Result.failure(Exception(response.body()?.message ?: "Gagal mengambil profil user"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
    
    /**
     * Get statistik absensi
     */
    suspend fun getStatistikAbsensi(token: String, bulan: Int? = null, tahun: Int? = null): Result<StatistikData> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getStatistikAbsensi("Bearer $token", bulan, tahun)
                if (response.isSuccessful && response.body()?.success == true) {
                    Result.success(response.body()!!.data)
                } else {
                    Result.failure(Exception(response.body()?.message ?: "Gagal mengambil statistik"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}
