package com.example.cryptoexchange.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinDtoObject(
    @SerializedName("CoinInfo") @Expose @JvmField var coinName: CoinName? = null
)
