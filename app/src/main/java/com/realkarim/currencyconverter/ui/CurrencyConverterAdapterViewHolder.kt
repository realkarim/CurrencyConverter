package com.realkarim.currencyconverter.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.realkarim.currencyconverter.ui.model.Currency
import kotlinx.android.synthetic.main.currency_list_item.view.*

class CurrencyConverterAdapterViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(currency: Currency) {
        view.name.text = currency.name
        view.etValue.setText(currency.rate.toString())
    }
}