package com.realkarim.currencyconverter.data.relay

import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Single

class AdjustedRatesRelay {

    private val clickEventRelay = BehaviorRelay.create<HashMap<String, Double>>()

    fun get(): Single<HashMap<String, Double>> = Single.fromObservable { clickEventRelay.take(1) }

    fun accept(map: HashMap<String, Double>) {
        clickEventRelay.accept(map)
    }
}