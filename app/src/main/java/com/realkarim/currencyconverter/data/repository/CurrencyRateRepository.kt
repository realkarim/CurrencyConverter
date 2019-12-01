package com.realkarim.currencyconverter.data.repository

import com.realkarim.currencyconverter.data.network.CurrencyRateApi

class CurrencyRateRepository(private val currencyRateApi: CurrencyRateApi) {
    fun fetchRateForCurrency(currency: String) = currencyRateApi.getTaxisForCoordinates(currency)
}