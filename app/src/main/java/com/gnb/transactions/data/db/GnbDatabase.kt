package com.gnb.transactions.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gnb.transactions.data.db.dao.RateDao
import com.gnb.transactions.data.db.dao.TransactionDao
import com.gnb.transactions.models.Rate
import com.gnb.transactions.models.Transaction

@Database(
    entities = [
        Rate::class,
        Transaction::class
    ],
    version = 1
)
abstract class GnbDatabase: RoomDatabase() {
    abstract fun rateDao(): RateDao
    abstract fun transactionDao(): TransactionDao
}