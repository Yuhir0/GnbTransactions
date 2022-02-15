package com.gnb.transactions.domain

import com.gnb.transactions.data.api.GnbApiService
import com.gnb.transactions.data.db.dao.RateDao

class RatesUseCase (
    val rateDao: RateDao,
    val api: GnbApiService
) {

}
