package com.example.cryptoexchange.data.mapper

import com.example.cryptoexchange.data.CoinEntity
import com.example.cryptoexchange.data.network.model.CoinDetailedInfo
import com.example.cryptoexchange.data.network.model.CoinInfoJsonContainerDto
import com.example.cryptoexchange.data.network.model.CoinNamesDto
import com.example.cryptoexchange.domain.CoinInfo
import com.google.gson.Gson
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class Mapper {

    private val gson = Gson()

    fun mapNamesListToString(listDto: CoinNamesDto): String {
        return listDto.data?.joinToString(",") { it.coinName?.name ?: "" } ?: ""
    }

    fun mapJsonContainerToListCoinInfo(jsonContainer: CoinInfoJsonContainerDto): List<CoinDetailedInfo> {
        val result = mutableListOf<CoinDetailedInfo>()
        val jsonObject = jsonContainer.json ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinDetailedInfo::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    fun mapDataDtoToEntity(detailedInfo: CoinDetailedInfo): CoinEntity {
        return CoinEntity(
            coinName = detailedInfo.fromsymbol.orEmpty(),
            toCurrency = detailedInfo.tosymbol.orEmpty(),
            coinPrice = detailedInfo.price ?: -1.0,
            lastUpdate = convertTimestampToTime(detailedInfo.lastupdate),
            high24hour = detailedInfo.high24hour ?: -1.0,
            low24hour = detailedInfo.low24hour ?: -1.0,
            imageUrl = BASE_IMAGE_URL + (detailedInfo.imageurl ?: "null")
        )
    }

    private fun convertTimestampToTime(timestamp: Long?): String {
        if (timestamp == null) return ""
        val stamp = Timestamp(timestamp * 1000)
        val date = Date(stamp.time)
        val pattern = "HH:mm:ss"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }

    fun dataDtosToEntities(dataDtos: List<CoinDetailedInfo>): List<CoinEntity> {
        return dataDtos.map { mapDataDtoToEntity(it) }
    }

    fun mapEntityToCoinInfo(coinEntity: CoinEntity): CoinInfo {
        return CoinInfo(
            coinName = coinEntity.coinName,
            toCurrency = coinEntity.toCurrency,
            coinPrice = coinEntity.coinPrice,
            lastUpdate = coinEntity.lastUpdate,
            high24hour = coinEntity.high24hour,
            low24hour = coinEntity.low24hour,
            imageUrl = coinEntity.imageUrl
        )
    }

    fun mapEntitiesToCoinsInfos(entities: List<CoinEntity>): List<CoinInfo> {
        return entities.map { mapEntityToCoinInfo(it) }
    }

    companion object {
        const val BASE_IMAGE_URL = "https://www.cryptocompare.com"
    }
}
