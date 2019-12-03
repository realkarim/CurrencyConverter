package com.realkarim.currencyconverter.domain.mapper

import com.realkarim.currencyconverter.data.model.CurrencyRateResponse
import com.realkarim.currencyconverter.ui.model.Currency
import com.realkarim.currencyconverter.ui.model.CurrencyViewData

object ModelMapper {
    fun mapToViewData(currencyRateResponse: CurrencyRateResponse): CurrencyViewData {
        val baseCurrency = currencyRateResponse.base ?: ""
        val rates = currencyRateResponse.rates
        var currencyList: MutableList<Currency> = mutableListOf()
        if (rates != null) {
            currencyList.add(Currency("AUD", rates.aUD))
            currencyList.add(Currency("BGN", rates.bGN))
            currencyList.add(Currency("BRL", rates.bRL))
            currencyList.add(Currency("CAD", rates.cAD))
            currencyList.add(Currency("CHF", rates.cHF))
            currencyList.add(Currency("CNY", rates.cNY))
            currencyList.add(Currency("CZK", rates.cZK))
            currencyList.add(Currency("DKK", rates.dKK))
            currencyList.add(Currency("GBP", rates.gBP))
            currencyList.add(Currency("HKD", rates.hKD))
            currencyList.add(Currency("HRK", rates.hRK))
            currencyList.add(Currency("HUF", rates.hUF))
            currencyList.add(Currency("IDR", rates.iDR))
            currencyList.add(Currency("ILS", rates.iLS))
            currencyList.add(Currency("INR", rates.iNR))
            currencyList.add(Currency("ISK", rates.iSK))
            currencyList.add(Currency("JPY", rates.jPY))
            currencyList.add(Currency("KRW", rates.kRW))
            currencyList.add(Currency("MXN", rates.mXN))
            currencyList.add(Currency("MYR", rates.mYR))
            currencyList.add(Currency("NOK", rates.nOK))
            currencyList.add(Currency("NZD", rates.nZD))
            currencyList.add(Currency("PHP", rates.pHP))
            currencyList.add(Currency("PLN", rates.pLN))
            currencyList.add(Currency("RON", rates.rON))
            currencyList.add(Currency("RUB", rates.rUB))
            currencyList.add(Currency("SEK", rates.sEK))
            currencyList.add(Currency("SGD", rates.sGD))
            currencyList.add(Currency("THB", rates.tHB))
            currencyList.add(Currency("TRY", rates.tRY))
            currencyList.add(Currency("USD", rates.uSD))
            currencyList.add(Currency("ZAR", rates.zAR))
        }
        return CurrencyViewData(baseCurrency, currencyList)
    }
}