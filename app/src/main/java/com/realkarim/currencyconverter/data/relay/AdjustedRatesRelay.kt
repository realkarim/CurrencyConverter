package com.realkarim.currencyconverter.data.relay

import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.Single

class AdjustedRatesRelay {

    private val clickEventRelay = BehaviorRelay.create<HashMap<String, Double>>()

    fun get(): Observable<HashMap<String, Double>> = clickEventRelay

    fun accept(map: HashMap<String, Double>) {
        clickEventRelay.accept(map)
    }
}