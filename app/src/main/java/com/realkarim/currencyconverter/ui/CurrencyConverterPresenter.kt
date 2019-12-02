package com.realkarim.currencyconverter.ui

import com.realkarim.currencyconverter.domain.interactor.GetCurrencyRateInteractor
import io.reactivex.disposables.CompositeDisposable

class CurrencyConverterPresenter(
    private val getCurrencyRateInteractor: GetCurrencyRateInteractor
) : CurrencyConverterContract.Presenter {

    private lateinit var view: CurrencyConverterContract.View
    private val compositeDisposable = CompositeDisposable()

    override fun attachView(view: CurrencyConverterContract.View) {
        this.view = view
    }

    override fun loadRateForCurrency(currency: String) {
        compositeDisposable.add(
            getCurrencyRateInteractor(currency)
                .map { it.toString() }
                .subscribe(
                    { view.updateView(it) },
                    { view.showErrorMessage(it.localizedMessage) }
                )
        )
    }

    override fun onStop() {
        compositeDisposable.clear()
    }
}