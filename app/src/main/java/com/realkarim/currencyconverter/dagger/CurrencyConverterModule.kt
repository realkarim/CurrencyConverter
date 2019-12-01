package com.realkarim.currencyconverter.dagger

import com.realkarim.currencyconverter.data.network.CurrencyRateApi
import com.realkarim.currencyconverter.data.repository.CurrencyRateRepository
import com.realkarim.currencyconverter.ui.CurrencyConverterContract
import com.realkarim.currencyconverter.ui.CurrencyConverterPresenter
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://revolut.duckdns.org/"

@Module
class CurrencyConverterModule {

    @Provides
    fun provideCurrencyConverterPresenter(): CurrencyConverterContract.Presenter =
        CurrencyConverterPresenter()

    @Provides
    fun provideCurrencyRateRepository(currencyRateApi: CurrencyRateApi) =
        CurrencyRateRepository(currencyRateApi)

    @Provides
    fun provideCurrencyRateApi(okHttpClient: OkHttpClient): CurrencyRateApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
            .create(CurrencyRateApi::class.java)

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()

        clientBuilder
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)

        return clientBuilder.build()
    }
}