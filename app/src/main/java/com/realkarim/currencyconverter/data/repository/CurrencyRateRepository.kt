package com.realkarim.currencyconverter.data.repository

import com.realkarim.currencyconverter.data.model.CurrencyRateResponse
import com.realkarim.currencyconverter.data.network.CurrencyRateApi
import com.realkarim.currencyconverter.data.relay.AdjustedRatesRelay

class CurrencyRateRepository(
    private val currencyRateApi: CurrencyRateApi,
    private val adjustedRatesRelay: AdjustedRatesRelay
) {

    private var baseEUR = 1.0
    private val adjustedRates = hashMapOf<String, Double>()

    fun fetchRateForCurrency(currency: String) =
        currencyRateApi.fetchRatesForCurrency(currency)
            .doOnSuccess {
                if (it != null)
                    adjustMeasure(it)
            }

    fun getAdjustedRates() = adjustedRates

    fun updateBaseEur(newBase: Double) {
        this.baseEUR = newBase
    }

    fun getBaseEur() = baseEUR

    private fun adjustMeasure(currencyRateResponse: CurrencyRateResponse) {
        val newRates = currencyRateResponse.rates ?: return
        for (key in newRates.keys) {
            if (baseEUR == 0.0)
                adjustedRates[key] = 0.0
            else
                adjustedRates[key] = (newRates[key] ?: 0.0) * baseEUR
        }

        adjustedRatesRelay.accept(adjustedRates)
    }
}