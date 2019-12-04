package com.realkarim.currencyconverter.ui

import com.realkarim.currencyconverter.ui.model.Currency
import com.realkarim.currencyconverter.ui.model.CurrencyViewData

interface CurrencyConverterContract {
    interface View {
        fun updateView(currencyViewData: CurrencyViewData)
        fun showErrorMessage(text: String)
    }

    interface ItemView {
        fun setName(name: String)
        fun setValue(value: Double)
    }

    interface Presenter {
        fun attachView(view: View)
        fun loadRateForCurrency(currency: String)
        fun onStop()
    }

    interface AdapterPresenter {
        fun bindViewItem(
            currencyConverterAdapterViewHolder: CurrencyConverterAdapterViewHolder,
            currency: Currency
        )
    }
}