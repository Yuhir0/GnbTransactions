package com.gnb.transactions.viwmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.gnb.transactions.domain.RatesUseCase

class DetailViewModel (
    appContext: Application,
    private val ratesUseCase: RatesUseCase
) : AndroidViewModel(appContext) {
    // TODO: Implement the ViewModel
}