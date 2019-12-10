package com.realkarim.currencyconverter.ui

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
    }

    interface Presenter {
        fun attachView(view: View)
        fun onViewItemClick(name: String, value: Double, position: Int)
        fun onStart()
        fun onStop()
        fun onCurrencyValueChanged(name: String, value: Double)
        fun isTopCurrency(name: String): Boolean
    }
}