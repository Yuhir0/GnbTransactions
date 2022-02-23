package com.gnb.transactions.domain

import com.gnb.transactions.data.api.GnbApiService
import com.gnb.transactions.data.db.dao.TransactionDao
import com.gnb.transactions.models.Transaction

private var loaded = false
class TransactionsUseCase (
    val transactionDao: TransactionDao,
    private val api: GnbApiService
) {
    suspend fun getDistinctProducts(): List<String> {
        return transactionDao.getDistinctProducts()
    }

    suspend fun loadTransactions() {
        if (!loaded) {
            val data = api.getGnbTransactions().execute()!!.body()
            saveTransactions(*data.toTypedArray())
            loaded = true
        }
    }

    suspend fun getAllTransactions(): List<Transaction> {
        return transactionDao.getAll()
    }

    suspend fun saveTransactions(vararg transactions: Transaction) {
        transactionDao.clearAndInsertAll(*transactions)
    }

    suspend fun getTransactionsBySku(sku: String): List<Transaction> {
        return transactionDao.getTransactionsBySku(sku)
    }
}
