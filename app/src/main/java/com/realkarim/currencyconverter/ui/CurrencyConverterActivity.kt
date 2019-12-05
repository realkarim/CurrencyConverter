package com.realkarim.currencyconverter.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import com.realkarim.currencyconverter.R
import com.realkarim.currencyconverter.dagger.DaggerCurrencyConverterComponent
import com.realkarim.currencyconverter.ui.model.CurrencyViewData
import kotlinx.android.synthetic.main.activity_currencyt_converter.*
import javax.inject.Inject

class CurrencyConverterActivity : AppCompatActivity(), CurrencyConverterContract.View {

    @Inject
    lateinit var presenter: CurrencyConverterContract.Presenter

    @Inject
    lateinit var adapter: CurrencyConverterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currencyt_converter)

        initDagger()

        currencyList.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        presenter.startPollingRatesForCurrency("EUR")
    }

    private fun initDagger() {
        DaggerCurrencyConverterComponent.create().inject(this)
        presenter.attachView(this)
    }

    override fun updateViewData(currencyViewData: CurrencyViewData) {
        adapter.updateData(currencyViewData)
    }

    override fun moveItemToTop(position: Int) {
        adapter.moveItemToTop(position)
    }

    override fun showErrorMessage(text: String) {
        Toast.makeText(this, text, LENGTH_SHORT).show()
        Log.e("CCError", text)
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }
}
