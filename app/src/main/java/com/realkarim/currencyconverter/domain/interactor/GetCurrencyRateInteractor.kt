package com.realkarim.currencyconverter.domain.interactor

import android.util.Log
import com.realkarim.currencyconverter.data.repository.CurrencyRateRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetCurrencyRateInteractor(private val currencyRateRepository: CurrencyRateRepository) {
    operator fun invoke(currency: String) = currencyRateRepository.fetchRateForCurrency(currency)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}