package com.example.cryptoexchange.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.cryptoexchange.data.mapper.Mapper
import com.example.cryptoexchange.data.workers.RefreshDataWorker
import com.example.cryptoexchange.domain.CoinInfo
import com.example.cryptoexchange.domain.Repository
import com.example.cryptoexchange.presentation.MainActivity.Companion.TAG

class RepositoryImpl(val application: Application): Repository {
    val liveData = MutableLiveData<List<CoinInfo>>()
    val mapper = Mapper()
    private val dao = CoinDataBase.getDatabase(application).coinDao()

    override fun getCurrencyList(): LiveData<List<CoinInfo>> {
        return MediatorLiveData<List<CoinInfo>>().apply {
            addSource(dao.getListEntities()){
                value = mapper.mapEntitiesToCoinsInfos(it)
                    .sortedByDescending { it.coinPrice }
            }
        }

    }

    override fun getCoin(coinName: String): LiveData<CoinInfo> {
        return MediatorLiveData<CoinInfo>().apply {
            addSource(dao.getEntity(coinName)){
                Log.d(TAG, "getCoin live data: $it")
                value = mapper.mapEntityToCoinInfo(it)
            }
        }

    }

    override fun loadData(){
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }

    override suspend fun removeCoin(coinInfo: CoinInfo) {
        //TODO(implement me)

    }



}