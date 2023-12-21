package com.example.cryptoexchange.data.network.model

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinInfoJsonContainerDto(
    @SerializedName("RAW") @Expose @JvmField val json: JsonObject? = null
)
