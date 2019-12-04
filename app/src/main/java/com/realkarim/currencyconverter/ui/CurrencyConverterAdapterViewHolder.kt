package com.realkarim.currencyconverter.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.currency_list_item.view.*

class CurrencyConverterAdapterViewHolder(private val view: View) :
    RecyclerView.ViewHolder(view),
    CurrencyConverterContract.ItemView {

    override fun setName(name: String) {
        view.name.text = name
    }

    override fun setValue(value: Double) {
        view.etValue.setText(value.toString())
    }
}