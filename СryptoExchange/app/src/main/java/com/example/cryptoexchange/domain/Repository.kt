package com.example.cryptoexchange.domain

import androidx.lifecycle.LiveData

interface Repository {

    fun getCurrencyList(): LiveData<List<CoinInfo>>
   fun getCoin(coinName: String): LiveData<CoinInfo>
    fun loadData()
    suspend fun removeCoin(coinInfo: CoinInfo)
}