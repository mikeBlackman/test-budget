package com.dynamiccontrols.myob.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    companion object {
        const val ACCESS_KEY = "aa6ea4223eae48a4bee0a988a5e4d11c"
    }

    data class CurrencyResponse(
        val success: Boolean,
        val timestamp: Long,
        val source : String,
        val quotes : Quote
    )

    data class Quote(val USDNZD : Double)

    @GET("live")
    suspend fun getRate(
        @Query("access_key") access_key: String,
        @Query("currencies") from: String,
        @Query("source") to: String,
        @Query("format") amount: Int
    ): Response<CurrencyResponse>

}