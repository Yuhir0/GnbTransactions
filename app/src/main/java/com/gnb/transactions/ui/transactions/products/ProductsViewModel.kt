package com.gnb.transactions.ui.transactions.products

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gnb.transactions.domain.TransactionsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.streams.toList

class ProductsViewModel (
    appContext: Application,
    private val transactionsUseCase: TransactionsUseCase
) : AndroidViewModel(appContext) {

    val products = MutableLiveData<List<String>>()

    fun launchDataLoad() = viewModelScope.launch {
        loadTransactions()
    }

    private suspend fun loadProducts() = withContext(Dispatchers.IO) {
        products.postValue(transactionsUseCase.getDistinctProducts())
    }

    private suspend fun loadTransactions() = withContext(Dispatchers.IO) {
        transactionsUseCase.loadTransactions()
        loadProducts()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun searchProduct(product: String): List<String> {
        return products.value!!.stream().filter {
            it.contains(product)
        }.toList()
    }
}