package com.example.cryptoexchange.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoexchange.R
import com.example.cryptoexchange.domain.CURRENCY_CODE
import com.example.cryptoexchange.domain.CoinInfo
import com.example.cryptoexchange.domain.UNDEFINED_COIN
import com.squareup.picasso.Picasso


class CoinDetailsFragment : Fragment() {
    private lateinit var coinLogo: ImageView
    private lateinit var coinNameTV: TextView
    private lateinit var baseCurrencyTV: TextView
    private lateinit var coinPriceTV: TextView
    private lateinit var min24HourTV: TextView
    private lateinit var max24HourTV: TextView
    private lateinit var updateTimeTV: TextView

    private lateinit var viewModel: CoinDetailViewModel
    var coinName: String = UNDEFINED_COIN

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_coin_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coinLogo = view.findViewById(R.id.coinLogoDetails)
        coinNameTV = view.findViewById(R.id.tvCoinName)
        baseCurrencyTV = view.findViewById(R.id.tvBaseCurrency)
        coinPriceTV = view.findViewById(R.id.tvCoinPrice)
        min24HourTV = view.findViewById(R.id.tvMin24h)
        max24HourTV = view.findViewById(R.id.tvMax24h)
        updateTimeTV = view.findViewById(R.id.tvLastUpdate)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate: ", )
        with(requireArguments()){
            if(containsKey(CURRENCY_CODE)){
                coinName = getString(CURRENCY_CODE) ?: UNDEFINED_COIN
            }
        }

        viewModel = ViewModelProvider(this)[CoinDetailViewModel::class.java]

        viewModel.getCoin(coinName)

        viewModel.liveData.observe(this){ coinInfo: CoinInfo ->
            Log.d(MainActivity.TAG, "live data detailed activity: $coinInfo")
            Picasso.get().load(coinInfo.imageUrl).into(coinLogo)
            coinNameTV.text = coinInfo.coinName
            baseCurrencyTV.text = coinInfo.toCurrency
            coinPriceTV.text = coinInfo.coinPrice.toString()
            min24HourTV.text = coinInfo.low24hour.toString()
            max24HourTV.text = coinInfo.high24hour.toString()
            updateTimeTV.text = coinInfo.lastUpdate

        }

    }

    companion object {
        const val TAG = "RRRRRR"
        fun newInstanceFragment(coinName: String) = CoinDetailsFragment().apply {
            arguments = Bundle().apply {
                putString(CURRENCY_CODE,coinName)
            }
        }
    }

}
