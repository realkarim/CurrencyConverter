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
    }

    interface Presenter {
        fun attachView(view: View)
        fun loadRateForCurrency(currency: String)
        fun bindViewItem(
            currencyConverterAdapterViewHolder: CurrencyConverterAdapterViewHolder,
            currency: Currency
        )
        fun onViewItemClick(position: Int)
        fun onStop()
    }
}