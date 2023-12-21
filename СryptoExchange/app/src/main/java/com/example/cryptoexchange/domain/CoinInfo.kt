package com.example.cryptoexchange.domain

data class CoinInfo(
    val coinName: String = "",
    val toCurrency: String = "",
    val coinPrice: Double = 0.0,
    val lastUpdate: String = "",
    val high24hour: Double = 0.0,
    val low24hour: Double = 0.0,
    val imageUrl: String = "",
)
