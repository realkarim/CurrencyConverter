package com.realkarim.currencyconverter.ui

import com.realkarim.currencyconverter.ui.model.Currency
import com.realkarim.currencyconverter.ui.model.CurrencyViewData

interface CurrencyConverterContract {
    interface View {
        fun updateViewData(currencyViewData: CurrencyViewData)
        fun moveItemToTop(position: Int)
        fun showErrorMessage(text: String)
    }

    interface ItemView {
        fun setName(name: String)
        fun setValue(value: Double)
        fun setListeners()
    }

    interface Presenter {
        fun attachView(view: View)
        fun startPollingRatesForCurrency(currency: String)
        fun onViewItemClick(position: Int)
        fun onStop()
    }
}