package com.example.cryptoexchange.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoexchange.R
import androidx.recyclerview.widget.ListAdapter
import com.example.cryptoexchange.domain.CoinInfo
import com.squareup.picasso.Picasso

class CoinAdapter
    : ListAdapter<CoinInfo,
        CoinAdapter.ShopItemViewHolder>(CurrencyDiffUtilCallbackAsync()){


    class ShopItemViewHolder(view: View):
        RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.currencyName)
        val rate: TextView = view.findViewById(R.id.rate)
        val cardView: CardView = view.findViewById(R.id.currency_card)
        val coinlogo: ImageView = view.findViewById(R.id.coinLogo)
        val timeUpdate: TextView = view.findViewById(R.id.updateTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.coin_layout, parent, false)

        return ShopItemViewHolder(view)
    }



    var clickListener: ((view: View, item: CoinInfo)->Unit  )? = null
    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {

        val coin = getItem(position)
        with(holder){
            "${coin.coinName} / ${coin.toCurrency}".also { name.text = it }
            rate.text = coin.coinPrice.toString()
            Picasso.get().load(coin.imageUrl).into(coinlogo)
            timeUpdate.text = String.format("Last update: %s",coin.lastUpdate)
            cardView.setOnClickListener {
                clickListener?.invoke(cardView, coin)
            }

        }
    }

    var swipeListener: ((item: CoinInfo) -> Unit)? = null
    var simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
        ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT

        ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            val position = viewHolder.adapterPosition
            val coinInfo = getItem(position)
            swipeListener?.invoke(coinInfo)
        }
    }
    

}