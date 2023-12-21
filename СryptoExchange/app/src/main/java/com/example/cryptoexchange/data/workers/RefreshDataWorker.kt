package com.example.cryptoexchange.data.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters

import com.example.cryptoexchange.data.CoinDataBase
import com.example.cryptoexchange.data.mapper.Mapper
import com.example.cryptoexchange.data.network.ApiFactory
import com.example.cryptoexchange.presentation.MainActivity.Companion.TAG
import kotlinx.coroutines.delay

class RefreshDataWorker(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context.applicationContext, workerParameters) {

    private val coinInfoDao = CoinDataBase.getDatabase(context).coinDao()
    private val apiService = ApiFactory(BASE_URL).service
    private val mapper = Mapper()
    private val key = "a86a86aea27a2aec11f601c8fbd8ea8bb2ec0988ecc2ef1829b7aff0927e00e6"


    override suspend fun doWork(): Result {
        while (true) {
            try {
                val topCoins = apiService.getCurrency(limit = 50, apiKey = key)
                val fSyms = mapper.mapNamesListToString(topCoins)
                val jsonContainer = apiService.getFullPriceList(fSyms = fSyms, apiKey = key)
                val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
                val coinEntityList = coinInfoDtoList.map { mapper.mapDataDtoToEntity(it) }
                coinInfoDao.insertPriceList(coinEntityList)
            } catch (e: Exception) {
                Log.e(TAG, "doWork: $e" )
            }
            delay(1000 * 10)
        }
    }

    companion object {

        const val NAME = "RefreshDataWorker"
        const val BASE_URL = "https://min-api.cryptocompare.com/"

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
        }
    }
}