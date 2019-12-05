package com.realkarim.currencyconverter.domain.interactor

import com.realkarim.currencyconverter.data.repository.CurrencyRateRepository
import com.realkarim.currencyconverter.domain.mapper.ModelMapper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class PollCurrencyRateInteractor(private val currencyRateRepository: CurrencyRateRepository) {
    operator fun invoke(currency: String) =
        Observable.interval(1, TimeUnit.SECONDS, Schedulers.io())
            .flatMap { currencyRateRepository.fetchRateForCurrency(currency).toObservable() }
            .map { ModelMapper.mapToViewData(it) }
            .observeOn(AndroidSchedulers.mainThread())
}