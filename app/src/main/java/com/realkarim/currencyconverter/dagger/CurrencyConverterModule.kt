package com.realkarim.currencyconverter.dagger

import com.realkarim.currencyconverter.data.network.CurrencyRateApi
import com.realkarim.currencyconverter.data.repository.CurrencyRateRepository
import com.realkarim.currencyconverter.domain.interactor.PollCurrencyRateInteractor
import com.realkarim.currencyconverter.ui.CurrencyConverterAdapter
import com.realkarim.currencyconverter.ui.CurrencyConverterContract
import com.realkarim.currencyconverter.ui.CurrencyConverterPresenter
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val BASE_URL = "https://revolut.duckdns.org/"

@Module
class CurrencyConverterModule {

    @Provides
    @Singleton
    fun provideCurrencyConverterPresenter(
        pollCurrencyRateInteractor: PollCurrencyRateInteractor
    ): CurrencyConverterContract.Presenter =
        CurrencyConverterPresenter(pollCurrencyRateInteractor)

    @Provides
    @Singleton
    fun provideCurrencyConverterAdapter(
        currencyConverterPresenter: CurrencyConverterContract.Presenter
    ): CurrencyConverterAdapter =
        CurrencyConverterAdapter(currencyConverterPresenter)

    @Provides
    @Singleton
    fun providePollCurrencyRateInteractor(currencyRateRepository: CurrencyRateRepository) =
        PollCurrencyRateInteractor(currencyRateRepository)

    @Provides
    @Singleton
    fun provideCurrencyRateRepository(currencyRateApi: CurrencyRateApi) =
        CurrencyRateRepository(currencyRateApi)

    @Provides
    @Singleton
    fun provideCurrencyRateApi(okHttpClient: OkHttpClient): CurrencyRateApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CurrencyRateApi::class.java)

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()

        clientBuilder
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)

        return clientBuilder.build()
    }
}