package com.gnb.transactions.data.api

import com.gnb.transactions.models.Rate
import com.gnb.transactions.models.Transaction
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface GnbApiService {
    @Headers("Accept: application/json")
    @GET("rates.json")
    fun getCurrencyRates(): Call<List<Rate>>

    @Headers("Accept: application/json")
    @GET("transactions")
    fun getGnbTransactions(): Call<List<Transaction>>
}