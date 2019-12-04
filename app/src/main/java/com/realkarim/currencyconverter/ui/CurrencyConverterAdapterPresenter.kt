package com.realkarim.currencyconverter.ui

import com.realkarim.currencyconverter.ui.model.Currency

class CurrencyConverterAdapterPresenter : CurrencyConverterContract.AdapterPresenter {
    override fun bindViewItem(
        currencyConverterAdapterViewHolder: CurrencyConverterAdapterViewHolder,
        currency: Currency
    ) {
        currencyConverterAdapterViewHolder.setName(currency.name)
        currencyConverterAdapterViewHolder.setValue(currency.rate)
    }
}