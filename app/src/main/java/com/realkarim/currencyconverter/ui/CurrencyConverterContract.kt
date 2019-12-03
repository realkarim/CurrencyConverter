package com.realkarim.currencyconverter.ui

import com.realkarim.currencyconverter.ui.model.CurrencyViewData

interface CurrencyConverterContract {
    interface View {
        fun updateView(currencyViewData: CurrencyViewData)
        fun showErrorMessage(text: String)
    }

    interface Presenter {
        fun attachView(view: View)
        fun loadRateForCurrency(currency: String)
        fun onStop()
    }
}