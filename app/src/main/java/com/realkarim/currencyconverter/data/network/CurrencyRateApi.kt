package com.realkarim.currencyconverter.data.network

import com.realkarim.currencyconverter.data.model.CurrencyRateResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyRateApi {
    @GET("/latest")
    fun fetchRatesForCurrency(@Query("base") base: String): Single<CurrencyRateResponse?>
}