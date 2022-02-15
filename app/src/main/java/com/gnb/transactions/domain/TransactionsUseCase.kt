package com.gnb.transactions.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gnb.transactions.data.api.GnbApiService
import com.gnb.transactions.data.db.dao.TransactionDao
import com.gnb.transactions.models.Transaction
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionsUseCase (
    val transactionDao: TransactionDao,
    private val api: GnbApiService
) {
    fun getTransactions(): LiveData<List<Transaction>> {
        val data = MutableLiveData<List<Transaction>>()
        api.getGnbTransactions().enqueue(
            object: Callback<List<Transaction>> {
                override fun onResponse(call: Call<List<Transaction>>?, response: Response<List<Transaction>>?) {
                    data.value = response!!.body()
                    Log.d("TransactionUseCase", data.value.toString())
                }

                override fun onFailure(call: Call<List<Transaction>>?, t: Throwable?) {
                    // Failure log
                }
            }
        )
        return data
    }
}
