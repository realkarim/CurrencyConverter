package com.realkarim.currencyconverter.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.realkarim.currencyconverter.R
import com.realkarim.currencyconverter.ui.model.CurrencyViewData

class CurrencyConverterAdapter(private val presenter: CurrencyConverterContract.Presenter) :
    RecyclerView.Adapter<CurrencyConverterAdapterViewHolder>() {

    private var currencyViewData: CurrencyViewData = CurrencyViewData()

    fun updateData(currencyViewData: CurrencyViewData) {
        this.currencyViewData = currencyViewData
        notifyDataSetChanged()
    }

    fun moveItemToTop(position: Int) {
        val item = currencyViewData.currencyList.get(position)
        currencyViewData.currencyList.removeAt(position)
        currencyViewData.currencyList.add(0, item)
        notifyItemMoved(position, 0)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CurrencyConverterAdapterViewHolder {
        return CurrencyConverterAdapterViewHolder(
            inflateView(parent, R.layout.currency_list_item),
            presenter
        )
    }

    override fun getItemCount(): Int {
        return currencyViewData.currencyList.size
    }

    override fun onBindViewHolder(holder: CurrencyConverterAdapterViewHolder, position: Int) =
        presenter.bindViewItem(holder, currencyViewData.currencyList.get(position))

    private fun inflateView(viewGroup: ViewGroup, @LayoutRes layout: Int) =
        LayoutInflater.from(viewGroup.context).inflate(layout, viewGroup, false)
}