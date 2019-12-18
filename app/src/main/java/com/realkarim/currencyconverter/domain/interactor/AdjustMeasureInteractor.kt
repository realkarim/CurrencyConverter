package com.realkarim.currencyconverter.domain.interactor

import com.realkarim.currencyconverter.data.repository.CurrencyRateRepository

class AdjustMeasureInteractor(private val currencyRateRepository: CurrencyRateRepository) {
    operator fun invoke(name: String, value: Double) {
        val adjustedRate = currencyRateRepository.getAdjustedRates()
        val oldValueForCurrency = adjustedRate[name] ?: 1.0
        currencyRateRepository.updateBaseEuro(
            adjustBase(
                currencyRateRepository.getBaseEur(),
                oldValueForCurrency,
                value
            )
        )
    }

    private fun adjustBase(oldBase: Double, oldValue: Double, newValue: Double) =
        oldBase * newValue / oldValue
}