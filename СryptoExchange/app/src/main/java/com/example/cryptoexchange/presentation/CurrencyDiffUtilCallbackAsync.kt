package com.example.cryptoexchange.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.cryptoexchange.domain.CoinInfo

class CurrencyDiffUtilCallbackAsync
    :DiffUtil.ItemCallback<CoinInfo>(){
    override fun areItemsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem.coinName == newItem.coinName
    }

    override fun areContentsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem == newItem
    }
}