package com.gnb.transactions.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "transaction")
@Parcelize
data class Transaction (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val sku: String,
    val amount: Double,
    val currency: String
) : Parcelable {
    override fun toString(): String {
        return "{" +
                "sku: $sku, " +
                "amount: $amount, " +
                "currency: $currency" +
                "}"
    }
}
