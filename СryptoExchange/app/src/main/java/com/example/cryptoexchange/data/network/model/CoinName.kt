package com.example.cryptoexchange.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinName(
    @SerializedName("Name") @Expose @JvmField var name: String? = null,
    @SerializedName("FullName") @Expose @JvmField var fullName: String? = null,
    @SerializedName("ImageUrl") @Expose @JvmField var imageUrl: String? = null
)
