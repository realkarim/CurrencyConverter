package com.realkarim.currencyconverter.ui

import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
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

        presenter.loadRateForCurrency("EUR")
    }

    private fun initDagger() {
        DaggerCurrencyConverterComponent.create().inject(this)
        presenter.attachView(this)
    }

    override fun updateView(text: String) {
        responseMessage.text = text
    }

    override fun showErrorMessage(text: String) {
        Toast.makeText(this, text, LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }
}
