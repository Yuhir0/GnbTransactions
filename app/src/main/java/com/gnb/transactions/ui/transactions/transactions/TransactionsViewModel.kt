package com.gnb.transactions.ui.transactions.transactions

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.gnb.transactions.domain.TransactionsUseCase

class TransactionsViewModel (
    appContext: Application,
    private val transactionsUseCase: TransactionsUseCase
) : AndroidViewModel(appContext) {

    val transactions = transactionsUseCase.getTransactions()
}