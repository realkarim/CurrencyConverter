package com.realkarim.currencyconverter.ui

import android.text.Editable
import android.text.TextWatcher
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
        view.setOnClickListener {
            view.etValue.requestFocus()
            presenter.onViewItemClick(
                view.name.text.toString(),
                view.etValue.text.toString().toDouble(),
                adapterPosition
            )
        }

        with(view.etValue) {
            setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    presenter.onViewItemClick(
                        view.name.text.toString(),
                        view.etValue.text.toString().toDouble(),
                        adapterPosition
                    )
                }
            }

            addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    // no-op
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    // no-op
                }

                override fun onTextChanged(value: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    val currency = view.name.text.toString()
                    val rate = value.toString().toDoubleOrNull() ?: 0.0
                    if (!value.isNullOrEmpty() && presenter.isTopCurrency(view.name.text.toString())) {
                        presenter.onCurrencyValueChanged(
                            currency,
                            rate
                        )
                    }
                }
            })
        }
    }
}