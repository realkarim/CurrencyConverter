package com.realkarim.currencyconverter.domain.interactor

import com.realkarim.currencyconverter.data.relay.AdjustedRatesRelay
import com.realkarim.currencyconverter.data.repository.CurrencyRateRepository
import com.realkarim.currencyconverter.domain.mapper.ModelMapper
import com.realkarim.currencyconverter.ui.model.CurrencyViewData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class PollCurrencyRateInteractor(
    private val currencyRateRepository: CurrencyRateRepository,
    private val adjustedRatesRelay: AdjustedRatesRelay
) {
    operator fun invoke(currency: String): Observable<CurrencyViewData> =
        Observable.merge(
            Observable.interval(2, TimeUnit.SECONDS, Schedulers.io())
                .flatMap { currencyRateRepository.fetchRateForCurrency(currency).toObservable() },
            adjustedRatesRelay.get()
        )
            .map { ModelMapper.mapToViewData(it) }
            .observeOn(AndroidSchedulers.mainThread())
}