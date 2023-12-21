package com.example.cryptoexchange.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cryptoexchange.R
import com.example.cryptoexchange.domain.CURRENCY_CODE
import com.example.cryptoexchange.domain.UNDEFINED_COIN
import com.example.cryptoexchange.presentation.MainActivity.Companion.TAG

class CoinDetailsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_details)
        if(savedInstanceState == null){
            parseIntent()
        }

    }

    fun setupFragment(fragment: CoinDetailsFragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.coinDetailsFragment,fragment)
            .commit()
    }
    var coinName: String = UNDEFINED_COIN
    fun parseIntent(){
        Log.d(TAG, "parseIntent: ")
        if (intent.hasExtra(CURRENCY_CODE)){
            coinName = intent.getStringExtra(CURRENCY_CODE) ?: UNDEFINED_COIN
            Log.d(TAG, "parseIntent: $coinName")
            setupFragment(CoinDetailsFragment.newInstanceFragment(coinName))
        }
    }


}