package com.gnb.transactions.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gnb.transactions.models.Rate
import com.gnb.transactions.models.Transaction
import androidx.room.Transaction as DbTransaction

@Dao
interface TransactionDao {
    @Query("SELECT * FROM `transaction`")
    suspend fun getAll(): List<Transaction>

    @Query("SELECT DISTINCT sku FROM `transaction`")
    suspend fun getDistinctProducts(): List<String>

    @Query("SELECT * FROM `transaction` WHERE sku = :sku")
    suspend fun getTransactionsBySku(sku: String): List<Transaction>

    @Insert
    suspend fun insertAll(vararg transactions: Transaction)

    @Delete
    suspend fun delete(vararg transactions: Transaction)

    @Query("DELETE FROM `transaction`")
    suspend fun deleteAll()

    @DbTransaction
    suspend fun clearAndInsertAll(vararg transactions: Transaction) {
        deleteAll()
        insertAll(*transactions)
    }
}