package com.fpadilha90.assetsinfo.ui.ui.assets

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fpadilha90.assetsinfo.R
import com.fpadilha90.domain.model.AssetInfo
import com.google.android.material.card.MaterialCardView


class AssetsAdapter(private var assets: List<AssetInfo>, private val assetClick: OnAssetClick) :
    RecyclerView.Adapter<AssetsAdapter.ViewHolder>() {

    class ViewHolder(val context: Context, view: View) : RecyclerView.ViewHolder(view) {
        val card: MaterialCardView = view.findViewById(R.id.card)
        val name: TextView = view.findViewById(R.id.name)
        val symbol: TextView = view.findViewById(R.id.symbol)
        val image: ImageView = view.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.asset_info_item, parent, false)

        return ViewHolder(parent.context, view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val assetInfo = assets[position]
        holder.name.text = assetInfo.name
        holder.symbol.text = assetInfo.symbol
        holder.card.setCardBackgroundColor(Color.parseColor(assetInfo.color))

        Glide
            .with(holder.context)
            .load(assetInfo.imageUrl)
            .centerCrop()
            .into(holder.image);

        holder.card.setOnClickListener {
            assetClick.onClick(assetInfo)
        }
    }

    override fun getItemCount() = assets.size

    fun update(newSet: List<AssetInfo>) {
        assets = newSet
        notifyDataSetChanged()
    }

    fun interface OnAssetClick {
        fun onClick(assetInfo: AssetInfo)
    }
}