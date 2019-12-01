package com.realkarim.currencyconverter.dagger

import com.realkarim.currencyconverter.ui.CurrencyConverterActivity
import dagger.Component

@Component(modules = [CurrencyConverterModule::class])
interface CurrencyConverterComponent {
    fun inject(currencyConverterActivity: CurrencyConverterActivity)
}