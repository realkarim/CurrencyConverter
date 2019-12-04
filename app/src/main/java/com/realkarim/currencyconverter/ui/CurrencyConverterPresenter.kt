package com.realkarim.currencyconverter.ui

import com.realkarim.currencyconverter.domain.interactor.GetCurrencyRateInteractor
import com.realkarim.currencyconverter.ui.model.Currency
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
                .subscribe(
                    { view.updateViewData(it) },
                    { view.showErrorMessage(it.localizedMessage) }
                )
        )
    }

    override fun bindViewItem(
        currencyConverterAdapterViewHolder: CurrencyConverterAdapterViewHolder,
        currency: Currency
    ) {
        currencyConverterAdapterViewHolder.setName(currency.name)
        currencyConverterAdapterViewHolder.setValue(currency.rate)
    }

    override fun onViewItemClick(position: Int) {
        view.moveItemToTop(position)
    }

    override fun onStop() {
        compositeDisposable.clear()
    }
}