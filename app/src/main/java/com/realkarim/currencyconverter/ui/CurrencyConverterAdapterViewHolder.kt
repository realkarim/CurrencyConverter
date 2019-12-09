package com.realkarim.currencyconverter.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.currency_list_item.view.*


class CurrencyConverterAdapterViewHolder(
    private val view: View,
    private val presenter: CurrencyConverterContract.Presenter
) : RecyclerView.ViewHolder(view), CurrencyConverterContract.ItemView {

    init {
        setListeners()
    }

    override fun setName(name: String) {
        view.name.text = name
    }

    override fun setValue(value: Double) {
        view.etValue.setText(value.toString())
    }

    private fun setListeners() {
        view.setOnClickListener { presenter.onViewItemClick(adapterPosition) }
//        with(view.etValue) {
//            addTextChangedListener(object : TextWatcher {
//                override fun afterTextChanged(p0: Editable?) {
//                }
//
//                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                }
//
//                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                    presenter.onCurrencyValueChanged(view.name, p0.toString().toDouble())
//                }
//            })
//        }
    }
}