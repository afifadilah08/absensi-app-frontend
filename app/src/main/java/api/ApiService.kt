package com.xirpl2.SASMobile.network

import com.xirpl2.SASMobile.model.ApiResponse
import com.xirpl2.SASMobile.model.AkunLoginResponse
import com.xirpl2.SASMobile.model.LoginRequest
import com.xirpl2.SASMobile.model.RegisterRequest
import com.xirpl2.SASMobile.model.JadwalSholatResponse
import com.xirpl2.SASMobile.model.RiwayatAbsensiResponse
import com.xirpl2.SASMobile.model.UserProfileResponse
import com.xirpl2.SASMobile.model.StatistikAbsensiResponse
import com.xirpl2.SASMobile.model.AbsensiRequest
import com.xirpl2.SASMobile.model.AbsensiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("api/register")
    suspend fun register(@Body request: RegisterRequest): Response<ApiResponse<RegisterRequest>>

    @POST("api/login")
    suspend fun login(@Body request: LoginRequest): Response<ApiResponse<AkunLoginResponse>>
    
    // ========== Beranda Endpoints ==========
    @GET("api/jadwal-sholat")
    suspend fun getJadwalSholat(
        @Header("Authorization") token: String
    ): Response<JadwalSholatResponse>
    
    @GET("api/jadwal-sholat/{tanggal}")
    suspend fun getJadwalSholatByDate(
        @Header("Authorization") token: String,
        @Path("tanggal") tanggal: String
    ): Response<JadwalSholatResponse>
    
    @GET("api/riwayat-absensi")
    suspend fun getRiwayatAbsensi(
        @Header("Authorization") token: String,
        @Query("limit") limit: Int = 10
    ): Response<RiwayatAbsensiResponse>
    
    @GET("api/user/profile")
    suspend fun getUserProfile(
        @Header("Authorization") token: String
    ): Response<UserProfileResponse>
    
    @GET("api/statistik-absensi")
    suspend fun getStatistikAbsensi(
        @Header("Authorization") token: String,
        @Query("bulan") bulan: Int? = null,
        @Query("tahun") tahun: Int? = null
    ): Response<StatistikAbsensiResponse>
    
    @POST("api/absensi")
    suspend fun submitAbsensi(
        @Header("Authorization") token: String,
        @Body absensiData: AbsensiRequest
    ): Response<AbsensiResponse>
}