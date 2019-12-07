package com.realkarim.currencyconverter.domain.mapper

import com.realkarim.currencyconverter.data.model.CurrencyRateResponse
import com.realkarim.currencyconverter.ui.model.Currency
import com.realkarim.currencyconverter.ui.model.CurrencyViewData

object ModelMapper {
    fun mapToViewData(currencyRateResponse: CurrencyRateResponse): CurrencyViewData {
        val currencyList: MutableList<Currency> = mutableListOf()
        val rates = currencyRateResponse.rates ?: emptyMap()

        if (!currencyRateResponse.base.isNullOrEmpty()) {
            currencyList.add(Currency(currencyRateResponse.base, 1.0))
        }
        for (key in rates.keys) {
            currencyList.add(Currency(key, rates.get(key) ?: 0.0))
        }

        return CurrencyViewData(currencyList)
    }
}