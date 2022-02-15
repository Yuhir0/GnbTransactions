package com.gnb.transactions.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "rate")
@Parcelize
data class Rate(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val from: String,
    val rate: Float,
    val to: String
) : Parcelable {
    override fun toString(): String {
        return "{" +
                "from: $from, " +
                "rate: $rate, " +
                "to: $to" +
                "}"
    }
}