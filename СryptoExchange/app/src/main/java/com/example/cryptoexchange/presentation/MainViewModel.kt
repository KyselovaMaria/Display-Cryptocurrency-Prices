package com.example.cryptoexchange.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.cryptoexchange.data.RepositoryImpl
import com.example.cryptoexchange.domain.CoinInfo
import com.example.cryptoexchange.domain.usecases.GetCoinListUseCase
import com.example.cryptoexchange.domain.usecases.LoadDataUseCase
import com.example.cryptoexchange.domain.usecases.RemoveCoinUseCase
import com.example.cryptoexchange.presentation.MainActivity.Companion.TAG
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repo = RepositoryImpl(application)
    private val getCoinListUseCase = GetCoinListUseCase(repo)
    private val loadDataUseCase = LoadDataUseCase(repo)
    private val removeCoinUseCase = RemoveCoinUseCase(repo)


    val liveData: LiveData<List<CoinInfo>>
            get() = getCoinListUseCase.getCurrencyList()



    override fun onCleared() {
        super.onCleared()
        Log.e(TAG, "onCleared: ViewModel deleted", )
    }
    fun loadData(){
        loadDataUseCase()
    }

    fun removeCoin(coinInfo: CoinInfo){
        viewModelScope.launch { removeCoinUseCase.removeCoin(coinInfo) }

    }
    init {
        loadData()
    }
}