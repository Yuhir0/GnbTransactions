package com.gnb.transactions.application

import androidx.room.Room
import com.gnb.transactions.data.api.GnbApiService
import com.gnb.transactions.data.db.GnbDatabase
import com.gnb.transactions.data.db.dao.RateDao
import com.gnb.transactions.data.db.dao.TransactionDao
import com.gnb.transactions.domain.RatesUseCase
import com.gnb.transactions.domain.TransactionsUseCase
import com.gnb.transactions.ui.transactions.detail.DetailViewModel
import com.gnb.transactions.ui.transactions.products.ProductsViewModel
import com.gnb.transactions.utils.CurrencyConversion
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataBase = module {
    fun provideTransactionDao(db: GnbDatabase): TransactionDao {
        return db.transactionDao()
    }

    fun provideRatesDao(db: GnbDatabase): RateDao {
        return db.rateDao()
    }

    single {
        Room.databaseBuilder(androidApplication(), GnbDatabase::class.java, "GnbDatabase")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { provideTransactionDao(get()) }
    single { provideRatesDao(get()) }
}

val apiService = module {
    single {
        Retrofit.Builder()
            .baseUrl("http://quiet-stone-2094.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GnbApiService::class.java)
    }
}

val viewModel = module {
    viewModel { ProductsViewModel(androidApplication(), get()) }
    viewModel { DetailViewModel(androidApplication(), get(), get()) }
}

val useCase = module {
    factory { TransactionsUseCase(get(), get()) }
    factory { RatesUseCase(get(), get()) }
}

val utils = module {
    factory { CurrencyConversion(get()) }
}
