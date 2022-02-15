package com.gnb.transactions.data.api

import com.gnb.transactions.models.Rate
import com.gnb.transactions.models.Transaction
import retrofit2.Call
import retrofit2.http.GET

interface GnbApiService {
    @GET("rates.json")
    fun getCurrencyRates(): Call<List<Rate>>

    @GET("transactions.json")
    fun getGnbTransactions(): Call<List<Transaction>>
}