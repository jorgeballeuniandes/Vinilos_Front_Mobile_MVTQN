package com.grupo20.vinilos.ui.collectors

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.grupo20.vinilos.R
import com.grupo20.vinilos.databinding.CollectorItemBinding
import com.grupo20.vinilos.modelos.Artist
import com.grupo20.vinilos.modelos.Collector
import com.grupo20.vinilos.ui.artists.ArtistFragmentDirections

class CollectorsAdapter : RecyclerView.Adapter<CollectorsAdapter.CollectorViewHolder>(){

    var collectors :List<Collector> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var navigator: NavController? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorViewHolder {
        val withDataBinding: CollectorItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CollectorViewHolder.LAYOUT,
            parent,
            false)
        return CollectorViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CollectorViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.collector = collectors[position]
        }
        holder.bind()
    }

    override fun getItemCount(): Int {
        return collectors.size
    }


    class CollectorViewHolder(val viewDataBinding: CollectorItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.collector_item
        }
        fun bind() {
            Glide.with(itemView)
                .load(R.drawable.ic_collectors)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.ic_collectors)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_collectors))
                .into(viewDataBinding.collectorImage)
        }
    }
}