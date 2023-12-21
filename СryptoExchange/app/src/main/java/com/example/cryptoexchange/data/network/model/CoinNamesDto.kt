package com.example.cryptoexchange.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinNamesDto(
    @SerializedName("Data") @Expose @JvmField var data: List<CoinDtoObject>? = null
)
