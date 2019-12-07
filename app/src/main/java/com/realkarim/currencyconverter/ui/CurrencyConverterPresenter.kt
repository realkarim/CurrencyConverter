package com.realkarim.currencyconverter.ui

import com.realkarim.currencyconverter.domain.interactor.PollCurrencyRateInteractor
import io.reactivex.disposables.CompositeDisposable

class CurrencyConverterPresenter(
    private val pollCurrencyRateInteractor: PollCurrencyRateInteractor
) : CurrencyConverterContract.Presenter {

    private lateinit var view: CurrencyConverterContract.View
    private val compositeDisposable = CompositeDisposable()

    override fun attachView(view: CurrencyConverterContract.View) {
        this.view = view
    }

    override fun startPollingRatesForCurrency(currency: String) {
        compositeDisposable.add(
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

    override fun onStop() {
        compositeDisposable.clear()
    }
}