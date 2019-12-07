package com.realkarim.currencyconverter.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.realkarim.currencyconverter.R
import com.realkarim.currencyconverter.ui.model.CurrencyViewData

class CurrencyConverterAdapter(private val presenter: CurrencyConverterContract.Presenter) :
    RecyclerView.Adapter<CurrencyConverterAdapterViewHolder>() {

    private val currencyViewData: CurrencyViewData = CurrencyViewData()
    private val currencyOrder: MutableList<String> = mutableListOf()

    fun updateData(updatedCurrencyRates: CurrencyViewData) {
        val rates = currencyViewData.currencyMap
        val updatedRates = updatedCurrencyRates.currencyMap
        for (key in updatedRates.keys) {
            if (key !in rates) {
                currencyOrder.add(key)
            }
            rates[key] = updatedRates[key]!!
        }
        notifyDataSetChanged()
    }

    fun moveItemToTop(position: Int) {
        val item = currencyOrder.get(position)
        currencyOrder.removeAt(position)
        currencyOrder.add(0, item)
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
        return currencyOrder.size
    }

    override fun onBindViewHolder(holder: CurrencyConverterAdapterViewHolder, position: Int) {
        holder.setName(currencyOrder.get(position))
        holder.setValue(currencyViewData.currencyMap[currencyOrder.get(position)] ?: 0.0)
        holder.setListeners()
    }

    private fun inflateView(viewGroup: ViewGroup, @LayoutRes layout: Int) =
        LayoutInflater.from(viewGroup.context).inflate(layout, viewGroup, false)
}