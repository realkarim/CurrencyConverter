package com.realkarim.currencyconverter.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.realkarim.currencyconverter.R
import com.realkarim.currencyconverter.dagger.DaggerCurrencyConverterComponent
import kotlinx.android.synthetic.main.activity_currencyt_converter.*
import javax.inject.Inject

class CurrencyConverterActivity : AppCompatActivity(), CurrencyConverterContract.View {

    @Inject
    lateinit var presenter: CurrencyConverterContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currencyt_converter)

        initDagger()

        responseMessage.text = presenter.loadRateForCurrency("TEST")
    }

    private fun initDagger() {
        DaggerCurrencyConverterComponent.create().inject(this)
    }
}
