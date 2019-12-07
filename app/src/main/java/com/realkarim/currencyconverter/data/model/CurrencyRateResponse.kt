package com.realkarim.currencyconverter.data.model


import com.google.gson.annotations.SerializedName

data class CurrencyRateResponse(
    @SerializedName("base")
    val base: String?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("rates")
    val rates: LinkedHashMap<String, Double>?
)