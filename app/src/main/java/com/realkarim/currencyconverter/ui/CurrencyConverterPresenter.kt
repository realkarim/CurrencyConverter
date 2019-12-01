package com.realkarim.currencyconverter.ui

class CurrencyConverterPresenter : CurrencyConverterContract.Presenter {
    override fun loadRateForCurrency(currency: String) = "Mocked response message for $currency"
}