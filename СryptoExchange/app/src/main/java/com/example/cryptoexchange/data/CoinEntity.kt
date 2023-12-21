package com.example.cryptoexchange.data
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "top_coins")
data class CoinEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "coin_name")
    val coinName: String,
    @ColumnInfo(name = "toCurrency")
    val toCurrency: String,
    @ColumnInfo(name = "coinPrice")
    val coinPrice: Double,
    @ColumnInfo(name = "lastUpdate")
    val lastUpdate: String,
    @ColumnInfo(name = "high24hour")
    val high24hour: Double,
    @ColumnInfo(name = "low24hour")
    val low24hour: Double,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String,
)
