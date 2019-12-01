package com.realkarim.currencyconverter.ui

interface CurrencyConverterContract {
    interface View
    interface Presenter {
        fun loadRateForCurrency(currency: String): String
    }
}