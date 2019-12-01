package com.realkarim.currencyconverter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.realkarim.currencyconverter.R
import kotlinx.android.synthetic.main.activity_currencyt_converter.*

class CurrencyConverterActivity : AppCompatActivity(), CurrencyConverterContract.View {

    private val currencyConverterPresenter = CurrencyConverterPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currencyt_converter)

        responseMessage.text = currencyConverterPresenter.loadRateForCurrency("TEST")
    }
}
