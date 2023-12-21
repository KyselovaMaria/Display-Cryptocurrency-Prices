package com.example.cryptoexchange.data.network

import com.example.cryptoexchange.data.network.model.CoinInfoJsonContainerDto
import com.example.cryptoexchange.data.network.model.CoinNamesDto
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    object ApiServiceConstants {
        const val QUERY_PARAM_API_KEY = "api_key"
        const val QUERY_PARAM_LIMIT = "limit"
        const val QUERY_PARAM_TO_SYMBOL = "tsym"
        const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"
        const val CURRENCY = "USD"
    }

    @GET("data/top/totalvolfull")
    suspend fun getCurrency(
        @Query(ApiServiceConstants.QUERY_PARAM_API_KEY) apiKey: String = "",
        @Query(ApiServiceConstants.QUERY_PARAM_LIMIT) limit: Int = 10,
        @Query(ApiServiceConstants.QUERY_PARAM_TO_SYMBOL) tSym: String = ApiServiceConstants.CURRENCY
    ): CoinNamesDto

    @GET("data/pricemultifull")
    suspend fun getFullPriceList(
        @Query(ApiServiceConstants.QUERY_PARAM_API_KEY) apiKey: String = "",
        @Query(ApiServiceConstants.QUERY_PARAM_FROM_SYMBOLS) fSyms: String,
        @Query(ApiServiceConstants.QUERY_PARAM_TO_SYMBOLS) tSyms: String = ApiServiceConstants.CURRENCY
    ): CoinInfoJsonContainerDto

}