package com.example.cryptoexchange.data
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface CoinDao {
    @Query("SELECT * FROM top_coins")
    fun getListEntities(): LiveData<List<CoinEntity>>

    @Query("SELECT * FROM top_coins WHERE coin_name = :coin LIMIT 1")
    fun getEntity(coin: String): LiveData<CoinEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCoinEntity(coinEntity: CoinEntity)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateCoinEntity(coinEntity: CoinEntity)

    @Query("DELETE FROM top_coins")
    suspend fun clearTable()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPriceList(priceList: List<CoinEntity>)
}
