package com.example.cryptoexchange.domain.usecases

import androidx.lifecycle.LiveData
import com.example.cryptoexchange.domain.CoinInfo
import com.example.cryptoexchange.domain.Repository

class GetCoinListUseCase(
    private val repository: Repository
) {
    fun getCurrencyList(): LiveData<List<CoinInfo>>{
        return repository.getCurrencyList()
    }
}