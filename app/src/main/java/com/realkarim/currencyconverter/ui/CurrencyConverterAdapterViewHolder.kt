package com.realkarim.currencyconverter.ui

import android.view.View
import android.view.View.OnFocusChangeListener
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.currency_list_item.view.*


class CurrencyConverterAdapterViewHolder(
    private val view: View,
    private val presenter: CurrencyConverterContract.Presenter
) : RecyclerView.ViewHolder(view), CurrencyConverterContract.ItemView {

    init {
        view.setOnClickListener { view.etValue.requestFocus() }
        view.etValue.setOnFocusChangeListener(OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                presenter.onViewItemClick(adapterPosition)
            }
        })
    }

    override fun setName(name: String) {
        view.name.text = name
    }

    override fun setValue(value: Double) {
        view.etValue.setText(value.toString())
    }
}