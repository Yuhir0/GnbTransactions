package com.gnb.transactions.ui.transactions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gnb.transactions.R

class TransactionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, TransactionsFragment.newInstance())
            .commit()
    }
}