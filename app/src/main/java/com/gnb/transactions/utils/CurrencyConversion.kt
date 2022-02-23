package com.gnb.transactions.utils

import android.util.Log
import com.gnb.transactions.domain.RatesUseCase
import com.gnb.transactions.models.Rate

class CurrencyConversion(private val ratesUseCase: RatesUseCase) {

    private val logLabel = this::class.simpleName

    suspend fun calculateConversion(from: String, to: String, amount: Float): Float {
        Log.d(logLabel, "calculateConversion")
        return searchConversion(from, to) * amount
    }

    suspend fun searchConversion(from: String, to: String): Float {
        Log.d(logLabel, "searchConversion")
        return round(ratesUseCase.getRate(from, to)?.rate ?: searchIndirectConversion(from, to), 2)
    }

    private suspend fun searchIndirectConversion(from: String, to: String): Float {
        Log.d(logLabel, "searchIndirectConversion -> to: $to, from: $from")

        var usedCurrency = ArrayList<String>()
        var findNext = to;
        var conversion = 1f
        do {
            usedCurrency.add(findNext)
            val rate = ratesUseCase.getRatesByTo(findNext, usedCurrency).first()
            Log.d(logLabel, rate.toString())
            conversion *= rate.rate
            findNext = rate.from
        } while (rate.from != from)

        ratesUseCase.saveNewRate(Rate(0, from, conversion, to))
        Log.d(logLabel,"$from, $to, $conversion")
        return conversion
    }
}