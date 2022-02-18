package com.gnb.transactions.ui.transactions.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.gnb.transactions.domain.RatesUseCase
import com.gnb.transactions.models.Currency
import com.gnb.transactions.utils.CurrencyConversion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DetailViewModel (
    appContext: Application,
    private val ratesUseCase: RatesUseCase,
    private val currencyConversion: CurrencyConversion
) : AndroidViewModel(appContext) {

    private val logLabel = this::class.simpleName

    val conversionList = MutableLiveData<List<Currency>>()

    suspend fun conversion(from: String, amount: Float) = withContext(Dispatchers.IO) {
        val currencies = ratesUseCase.getDistinctCurrencyExcluding(from)
        Log.d(logLabel, currencies.toString())
        val converted = ArrayList<Currency>()
        for (currency in currencies) {
            val rate = currencyConversion.calculateConversion(from, currency, amount)
            converted.add(Currency(currency, rate*amount))
        }
        conversionList.postValue(converted)
    }
}