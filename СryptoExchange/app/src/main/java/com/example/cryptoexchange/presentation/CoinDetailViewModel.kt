package com.example.cryptoexchange.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.cryptoexchange.data.RepositoryImpl
import com.example.cryptoexchange.domain.CoinInfo
import com.example.cryptoexchange.domain.usecases.GetCoinUseCase
import com.example.cryptoexchange.domain.usecases.LoadDataUseCase

class CoinDetailViewModel(application: Application)
    : AndroidViewModel(application) {

    private val repo = RepositoryImpl(application)
    private val getCoinUseCase = GetCoinUseCase(repo)
    private val loadDataUseCase = LoadDataUseCase(repo)

    private lateinit var _liveData: LiveData<CoinInfo>
    val liveData: LiveData<CoinInfo>
        get() = _liveData
    init {
        loadDataUseCase()
    }
    fun getCoin(coinName: String){
        _liveData = getCoinUseCase.getCurrency(coinName)
    }

}