package com.example.cryptoexchange.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinDetailedInfo(
    @SerializedName("FROMSYMBOL") @Expose @JvmField var fromsymbol: String = "",
    @SerializedName("TOSYMBOL") @Expose @JvmField var tosymbol: String = "",
    @SerializedName("MEDIAN") @Expose @JvmField var median: Double? = null,
    @SerializedName("PRICE") @Expose @JvmField var price: Double? = null,
    @SerializedName("LASTUPDATE") @Expose @JvmField var lastupdate: Long? = null,
    @SerializedName("OPENHOUR") @Expose @JvmField var openhour: Double? = null,
    @SerializedName("HIGHHOUR") @Expose @JvmField var highhour: Double? = null,
    @SerializedName("LOWHOUR") @Expose @JvmField var lowhour: Double? = null,
    @SerializedName("OPENDAY") @Expose @JvmField var openday: Double? = null,
    @SerializedName("HIGHDAY") @Expose @JvmField var highday: Double? = null,
    @SerializedName("LOWDAY") @Expose @JvmField var lowday: Double? = null,
    @SerializedName("OPEN24HOUR") @Expose @JvmField var open24hour: Double? = null,
    @SerializedName("HIGH24HOUR") @Expose @JvmField var high24hour: Double? = null,
    @SerializedName("LOW24HOUR") @Expose @JvmField var low24hour: Double? = null,
    @SerializedName("IMAGEURL") @Expose @JvmField var imageurl: String? = null
)
