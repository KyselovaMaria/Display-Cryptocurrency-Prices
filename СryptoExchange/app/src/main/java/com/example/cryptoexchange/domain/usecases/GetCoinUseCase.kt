package com.example.cryptoexchange.domain.usecases

import androidx.lifecycle.LiveData
import com.example.cryptoexchange.domain.CoinInfo
import com.example.cryptoexchange.domain.Repository

class GetCoinUseCase(
    private val repository: Repository
) {
    fun getCurrency(coinName: String): LiveData<CoinInfo> =  repository.getCoin(coinName)
}