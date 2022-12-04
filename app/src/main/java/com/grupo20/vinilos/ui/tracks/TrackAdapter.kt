package com.grupo20.vinilos.ui.tracks

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.grupo20.vinilos.R
import com.grupo20.vinilos.databinding.AlbumItemBinding
import com.grupo20.vinilos.databinding.TrackItemBinding
import com.grupo20.vinilos.modelos.Album
import com.grupo20.vinilos.modelos.Track
import com.grupo20.vinilos.ui.albums.AlbumFragmentDirections
import com.grupo20.vinilos.ui.albums.AlbumsAdapter

class TrackAdapter : RecyclerView.Adapter<TrackAdapter.TrackViewHolder>(){

        var tracks :List<Track> = emptyList()
            @SuppressLint("NotifyDataSetChanged")
            set(value) {
                field = value
                notifyDataSetChanged()
            }
        var navigator: NavController? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
            val withDataBinding: TrackItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                TrackViewHolder.LAYOUT,
                parent,
                false)
            return TrackViewHolder(withDataBinding)
        }

        override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
            holder.viewDataBinding.also {
                it.track = tracks[position]
            }
            /*holder.viewDataBinding.root.setOnClickListener {
                val action = AlbumFragmentDirections.actionNavigationAlbumsToAlbumDetailFragment(albums[position])
                navigator?.navigate(action)
            }*/
        }

        override fun getItemCount(): Int {
            return tracks.size
        }

        class TrackViewHolder(val viewDataBinding: TrackItemBinding) :
            RecyclerView.ViewHolder(viewDataBinding.root) {
            companion object {
                @LayoutRes
                val LAYOUT = R.layout.track_item
            }
        }
    }