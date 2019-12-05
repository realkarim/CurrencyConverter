package com.realkarim.currencyconverter.ui

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.OnFocusChangeListener
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.currency_list_item.view.*


class CurrencyConverterAdapterViewHolder(
    private val view: View,
    private val presenter: CurrencyConverterContract.Presenter
) : RecyclerView.ViewHolder(view), CurrencyConverterContract.ItemView {

    override fun setName(name: String) {
        view.name.text = name
    }

    override fun setValue(value: Double) {
        view.etValue.setText(value.toString())
    }

    override fun setListeners() {
        view.setOnClickListener { view.etValue.requestFocus() }
        with(view.etValue) {
            setOnFocusChangeListener(OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    presenter.onViewItemClick(adapterPosition)
                }
            })
        }
    }
}