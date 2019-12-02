package com.realkarim.currencyconverter.ui

interface CurrencyConverterContract {
    interface View {
        fun updateView(text: String)
        fun showErrorMessage(text: String)
    }

    interface Presenter {
        fun attachView(view: View)
        fun loadRateForCurrency(currency: String)
        fun onStop()
    }
}