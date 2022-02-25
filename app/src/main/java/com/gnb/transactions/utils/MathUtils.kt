package com.gnb.transactions.utils

import java.math.BigDecimal


fun roundHalfToEven(x: Double, decimals: Int): Double {
    return BigDecimal(x).setScale(decimals, BigDecimal.ROUND_HALF_EVEN).toDouble()
}