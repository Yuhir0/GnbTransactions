package com.gnb.transactions.utils

import kotlin.math.roundToInt


fun round(value: Float, decimals: Int): Float {
    val rounder = 10f * decimals
    return (value * rounder).roundToInt() / rounder
}