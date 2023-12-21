package com.example.cryptoexchange.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CoinEntity::class], version = 1, exportSchema = false)
abstract class CoinDataBase : RoomDatabase() {
    abstract fun coinDao(): CoinDao

    companion object {
        @Volatile
        private var INSTANCE: CoinDataBase? = null

        fun getDatabase(context: Context): CoinDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CoinDataBase::class.java,
                    "coin_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
