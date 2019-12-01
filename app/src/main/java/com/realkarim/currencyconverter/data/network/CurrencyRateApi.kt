package com.realkarim.currencyconverter.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyRateApi {
    @GET("latest/{base}")
    fun getTaxisForCoordinates(@Path("base") base: String): Single<CurrencyRateApi?>
}