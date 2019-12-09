package com.realkarim.currencyconverter.ui

import com.realkarim.currencyconverter.domain.interactor.PollCurrencyRateInteractor
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class CurrencyConverterPresenter(
    private val pollCurrencyRateInteractor: PollCurrencyRateInteractor
) : CurrencyConverterContract.Presenter {

    private lateinit var view: CurrencyConverterContract.View
    private val compositeDisposable = CompositeDisposable()

    override fun onStart() {
        startPollingRatesForCurrency("EUR")
    }

    override fun onStop() {
        compositeDisposable.clear()
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

    override fun onViewItemClick(position: Int) {
        view.moveItemToTop(position)
    }

    private fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}