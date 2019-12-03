package com.realkarim.currencyconverter.domain.interactor

import com.realkarim.currencyconverter.data.repository.CurrencyRateRepository
import com.realkarim.currencyconverter.domain.mapper.ModelMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetCurrencyRateInteractor(private val currencyRateRepository: CurrencyRateRepository) {
    operator fun invoke(currency: String) = currencyRateRepository.fetchRateForCurrency(currency)
        .map { ModelMapper.mapToViewData(it) }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}