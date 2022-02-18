package com.gnb.transactions.ui.transactions

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gnb.transactions.R
import com.gnb.transactions.domain.RatesUseCase
import com.gnb.transactions.models.Rate
import com.gnb.transactions.ui.transactions.transactions.TransactionsFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class TransactionsActivity : AppCompatActivity() {
    private val rateUseCase: RatesUseCase by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, TransactionsFragment.newInstance())
            .commit()

        loadRates()
    }

    private suspend fun saveRates(rates: List<Rate>) = withContext(Dispatchers.IO) {
        rateUseCase.removeAll(*rateUseCase.getAllRates().toTypedArray())
        rateUseCase.saveRates(*rates.toTypedArray())
    }

    private fun loadRates() {
        val rates = rateUseCase.loadRates()
        rates.observe(this, { rates ->
            GlobalScope.launch(Dispatchers.IO) {
                saveRates(rates)
            }
        })
    }
}