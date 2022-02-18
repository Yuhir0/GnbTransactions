package com.gnb.transactions.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gnb.transactions.models.Transaction

@Dao
interface TransactionDao {
    @Query("SELECT * FROM `transaction`")
    suspend fun getAll(): List<Transaction>

    @Insert
    suspend fun insertAll(vararg transactions: Transaction)

    @Delete
    suspend fun deleteAll(vararg transactions: Transaction)
}