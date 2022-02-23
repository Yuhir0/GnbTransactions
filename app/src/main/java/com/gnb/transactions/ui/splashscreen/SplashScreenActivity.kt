package com.gnb.transactions.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gnb.transactions.databinding.ActivitySplashScreenBinding
import com.gnb.transactions.ui.transactions.TransactionsActivity


class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startActivity(Intent(this, TransactionsActivity::class.java))
    }

}