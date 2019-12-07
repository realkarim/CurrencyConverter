package com.realkarim.currencyconverter.domain.mapper

import com.realkarim.currencyconverter.data.model.CurrencyRateResponse
import com.realkarim.currencyconverter.ui.model.CurrencyViewData

object ModelMapper {
    fun mapToViewData(currencyRateResponse: CurrencyRateResponse): CurrencyViewData {
        val currencyMap: LinkedHashMap<String, Double> = linkedMapOf()
        val rates = currencyRateResponse.rates ?: linkedMapOf()

        if (!currencyRateResponse.base.isNullOrEmpty()) {
            currencyMap[currencyRateResponse.base] = 1.0
        }
        for (key in rates.keys) {
            if (rates[key] != null) {
                currencyMap[key] = rates[key]!!
            }
        }

        return CurrencyViewData(currencyMap)
    }
}