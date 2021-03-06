package com.gnb.transactions.data.db.dao

import androidx.room.*
import com.gnb.transactions.models.Rate

@Dao
interface RateDao {
    @Query("SELECT * FROM rate")
    suspend fun getAll(): List<Rate>

    @Query("SELECT * FROM rate WHERE `from` = :from AND `to` = :to LIMIT 1")
    suspend fun getRate(from: String, to: String): Rate?

    @Query("SELECT * FROM rate WHERE `to` == :to AND `from` not in (:notFrom)")
    suspend fun getRatesByTo(to: String, notFrom: List<String>?): List<Rate>

    @Query("SELECT DISTINCT `from` FROM rate WHERE `from` != :excluded")
    suspend fun getDistinctCurrencyExcluding(excluded: String): List<String>

    @Insert
    suspend fun insert(rate: Rate)

    @Insert
    suspend fun insertAll(vararg rates: Rate)

    @Delete
    suspend fun delete(vararg rates: Rate)

    @Query("DELETE FROM `rate`")
    fun deleteAll()

    @Transaction
    suspend fun clearAndInsertAll(vararg rates: Rate) {
        deleteAll()
        insertAll(*rates)
    }
}