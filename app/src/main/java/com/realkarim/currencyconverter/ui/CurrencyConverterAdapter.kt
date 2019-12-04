package com.realkarim.currencyconverter.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.realkarim.currencyconverter.R
import com.realkarim.currencyconverter.ui.model.CurrencyViewData

class CurrencyConverterAdapter(private val adapterPresenter: CurrencyConverterContract.AdapterPresenter) :
    RecyclerView.Adapter<CurrencyConverterAdapterViewHolder>() {

    private var currencyViewData: CurrencyViewData = CurrencyViewData()

    fun updateData(currencyViewData: CurrencyViewData) {
        this.currencyViewData = currencyViewData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CurrencyConverterAdapterViewHolder {
        return CurrencyConverterAdapterViewHolder(inflateView(parent, R.layout.currency_list_item))
    }

    override fun getItemCount(): Int {
        return currencyViewData.currencyList.size
    }

    override fun onBindViewHolder(holder: CurrencyConverterAdapterViewHolder, position: Int) =
        adapterPresenter.bindViewItem(holder, currencyViewData.currencyList.get(position))

    private fun inflateView(viewGroup: ViewGroup, @LayoutRes layout: Int) =
        LayoutInflater.from(viewGroup.context).inflate(layout, viewGroup, false)
}