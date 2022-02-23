package com.gnb.transactions.ui.transactions.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gnb.transactions.domain.TransactionsUseCase
import com.gnb.transactions.models.Transaction
import com.gnb.transactions.utils.CurrencyConversion
import com.gnb.transactions.utils.round
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel (
    appContext: Application,
    private val transactionsUseCase: TransactionsUseCase,
    private val currencyConversion: CurrencyConversion
) : AndroidViewModel(appContext) {

    private val logLabel = this::class.simpleName
    private val sumCurrency = "EUR"

    val transactions = MutableLiveData<List<Transaction>>()
    val total = MutableLiveData<Float>()

    suspend fun loadTransactionByProduct(product: String) = withContext(Dispatchers.IO){
        val data = transactionsUseCase.getTransactionsBySku(product)
        sumTransactionsTotal(data)
        transactions.postValue(data)
    }

    private suspend fun sumTransactionsTotal(data: List<Transaction>) {
        var sum = 0f
        for (trans in data) {
            sum += if (trans.currency == sumCurrency){
                trans.amount
            } else {
                val conversion = currencyConversion.searchConversion(trans.currency, sumCurrency)
                trans.amount * conversion
            }
        }
        total.postValue(round(sum, 2))
    }

    fun launchLoadData(product: String) = viewModelScope.launch {
        loadTransactionByProduct(product)
    }
}