package com.realkarim.currencyconverter.ui.model

data class CurrencyViewData(
    val baseCurrency: String = "",
    val currencyList: MutableList<Currency> = mutableListOf()
)