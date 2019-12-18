package com.realkarim.currencyconverter.ui

import com.realkarim.currencyconverter.domain.interactor.AdjustMeasureInteractor
import com.realkarim.currencyconverter.domain.interactor.PollCurrencyRateInteractor
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class CurrencyConverterPresenter(
    private val pollCurrencyRateInteractor: PollCurrencyRateInteractor,
    private val adjustMeasureInteractor: AdjustMeasureInteractor
) : CurrencyConverterContract.Presenter {

    private lateinit var view: CurrencyConverterContract.View
    private val compositeDisposable = CompositeDisposable()

    override fun onStart() {
        startPollingRatesForCurrency("EUR")
    }

    override fun onStop() {
        compositeDisposable.clear()
    }

    override fun onCurrencyValueChanged(name: String, value: Double) {
        adjustMeasureInteractor(name, value)
    }

    override fun isTopCurrency(name: String): Boolean {
        return true
    }

    override fun attachView(view: CurrencyConverterContract.View) {
        this.view = view
    }

    private fun startPollingRatesForCurrency(currency: String) {
        addDisposable(
            pollCurrencyRateInteractor(currency)
                .subscribe(
                    { view.updateViewData(it) },
                    { view.showErrorMessage(it.localizedMessage) }
                )
        )
    }

    override fun onViewItemClick(name: String, value: Double, position: Int) {
        adjustMeasureInteractor(name, value)
        view.moveItemToTop(position)
    }

    private fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}