package com.example.cryptoexchange.domain.usecases

import com.example.cryptoexchange.domain.Repository

class LoadDataUseCase(
    private val repository: Repository
) {
    operator fun invoke() = repository.loadData()
}