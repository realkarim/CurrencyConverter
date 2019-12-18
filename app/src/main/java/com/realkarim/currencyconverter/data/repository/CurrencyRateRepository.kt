package com.realkarim.currencyconverter.data.repository

import android.util.Log
import com.realkarim.currencyconverter.data.model.CurrencyRateResponse
import com.realkarim.currencyconverter.data.network.CurrencyRateApi
import com.realkarim.currencyconverter.data.relay.AdjustedRatesRelay

class CurrencyRateRepository(
    private val currencyRateApi: CurrencyRateApi,
    private val adjustedRatesRelay: AdjustedRatesRelay
) {

    private var baseEuro = 1.0
    private val adjustedRates = hashMapOf<String, Double>()

    init {
        adjustedRates["EUR"] = baseEuro
    }

    fun fetchRateForCurrency(currency: String) =
        currencyRateApi.fetchRatesForCurrency(currency)
            .map { adjustCurrencyRatesBasedOnEuro(it) }

    fun getAdjustedRates() = adjustedRates

    fun updateBaseEuro(newBaseEuro: Double) {
        adjustCurrencyRatesBasedOnEuro(newBaseEuro)
    }

    fun getBaseEur() = baseEuro

    private fun adjustCurrencyRatesBasedOnEuro(currencyRateResponse: CurrencyRateResponse): HashMap<String, Double> {
        val newRates = currencyRateResponse.rates ?: return adjustedRates
        adjustedRates["EUR"] = baseEuro
        for (key in newRates.keys) {
            if (baseEuro == 0.0)
                adjustedRates[key] = 0.0
            else
                adjustedRates[key] = (newRates[key] ?: 0.0) * baseEuro
        }
        return adjustedRates
    }

    private fun adjustCurrencyRatesBasedOnEuro(newBaseEuro: Double) {
        val newRates = adjustedRates
        for (key in newRates.keys) {
            if (baseEuro == 0.0)
                adjustedRates[key] = 0.0
            else {
                adjustedRates[key] = (newRates[key] ?: 0.0) / baseEuro
                adjustedRates[key] = (newRates[key] ?: 0.0) * newBaseEuro
            }
        }
        baseEuro = newBaseEuro
        adjustedRatesRelay.accept(adjustedRates)
    }
}