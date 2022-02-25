package com.gnb.transactions.ui.transactions

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.gnb.transactions.R
import com.gnb.transactions.domain.RatesUseCase
import com.gnb.transactions.domain.TransactionsUseCase
import com.gnb.transactions.models.Rate
import com.gnb.transactions.ui.transactions.products.ProductsFragment
import com.gnb.transactions.utils.roundHalfToEven
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class TransactionsActivity : AppCompatActivity() {

    private val logLabel = this::class.simpleName
    private val rateUseCase: RatesUseCase by inject { parametersOf(this) }
    private val transactionsUseCase: TransactionsUseCase by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadRates()

        Log.d(logLabel, "######################### ${roundHalfToEven(19.881234123121231242342, 2)}")

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, ProductsFragment.newInstance())
            .commit()
    }

    private fun loadRates() {
        val rates = rateUseCase.loadRates()
        rates.observe(this, {
            GlobalScope.launch(Dispatchers.IO) {
                saveRates(it)
            }
        })
    }

    private suspend fun saveRates(rates: List<Rate>) = withContext(Dispatchers.IO) {
        rateUseCase.saveRates(*rates.toTypedArray())
    }
}