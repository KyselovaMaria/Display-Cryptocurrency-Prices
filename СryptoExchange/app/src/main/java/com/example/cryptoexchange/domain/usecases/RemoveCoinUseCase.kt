package com.example.cryptoexchange.domain.usecases

import com.example.cryptoexchange.domain.CoinInfo
import com.example.cryptoexchange.domain.Repository

class RemoveCoinUseCase(
    val repository: Repository
) {
    suspend fun removeCoin(coinInfo: CoinInfo) = repository.removeCoin(coinInfo)
}