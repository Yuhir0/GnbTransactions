package com.gnb.transactions.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gnb.transactions.models.Rate

@Dao
interface RateDao {
    @Query("SELECT * FROM rate")
    fun getAll(): List<Rate>

    @Query("SELECT * FROM rate WHERE `from` = (:currency)")
    fun getFromCurrency(currency: String): List<Rate>

    @Insert
    fun insertAll(vararg rates: Rate)

    @Delete
    fun deleteAll(vararg rates: Rate)
}