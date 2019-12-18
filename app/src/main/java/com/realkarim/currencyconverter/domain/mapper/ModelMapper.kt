package com.realkarim.currencyconverter.domain.mapper

import com.realkarim.currencyconverter.data.model.CurrencyRateResponse
import com.realkarim.currencyconverter.ui.model.CurrencyViewData

object ModelMapper {
    fun mapToViewData(currencyRateResponse: HashMap<String, Double>): CurrencyViewData {
        val currencyMap: LinkedHashMap<String, Double> = linkedMapOf()

        if (!currencyRateResponse.isNullOrEmpty()) {
            currencyMap["EUR"] = 1.0
        }
        for (key in currencyRateResponse.keys) {
            if (currencyRateResponse[key] != null) {
                currencyMap[key] = currencyRateResponse[key]!!
            }
        }

        return CurrencyViewData(currencyMap)
    }
}