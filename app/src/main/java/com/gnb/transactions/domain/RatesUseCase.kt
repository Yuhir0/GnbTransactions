package com.gnb.transactions.domain

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.gnb.transactions.data.api.GnbApiService
import com.gnb.transactions.data.db.dao.RateDao
import com.gnb.transactions.models.Rate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RatesUseCase (
    val rateDao: RateDao,
    val api: GnbApiService
) {
    private val logLabel = this::class.simpleName

    fun loadRates(): MutableLiveData<List<Rate>> {
        val data = MutableLiveData<List<Rate>>()
        api.getCurrencyRates().enqueue(
            object: Callback<List<Rate>> {
                override fun onResponse(call: Call<List<Rate>>?, response: Response<List<Rate>>?) {
                    val responseDate = response!!.body()
                    data.value = responseDate

                    Log.d(logLabel, data.value?.size.toString())
                    Log.d(logLabel, data.value.toString())
                }

                override fun onFailure(call: Call<List<Rate>>?, t: Throwable?) {
                    Log.e(logLabel, t?.message ?: "Unknown Error")
                }
            }
        )
        return data
    }

    suspend fun saveRates(vararg rates: Rate) {
        rateDao.insertAll(*rates)
    }

    suspend fun getAllRates(): List<Rate> {
        return rateDao.getAll()
    }

    suspend fun getRate(from: String, to: String): Rate? {
        return rateDao.getRate(from, to)
    }

    suspend fun getRatesByTo(to: String, notFrom: List<String>?): List<Rate> {
        return rateDao.getRatesByTo(to, notFrom)
    }

    suspend fun getDistinctCurrencyExcluding(excluded: String): List<String> {
        return rateDao.getDistinctCurrencyExcluding(excluded)
    }

    suspend fun saveNewRate(rate: Rate) {
        rateDao.insert(rate)
    }

    suspend fun removeAll(vararg rates: Rate) {
        rateDao.deleteAll(*rates)
    }

}
