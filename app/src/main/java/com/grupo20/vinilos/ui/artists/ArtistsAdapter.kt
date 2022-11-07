package com.grupo20.vinilos.ui.artists

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.grupo20.vinilos.R
import com.grupo20.vinilos.databinding.ArtistItemBinding
import com.grupo20.vinilos.modelos.Artist
import com.squareup.picasso.Picasso

class ArtistsAdapter : RecyclerView.Adapter<ArtistsAdapter.ArtistViewHolder>(){

    var artists :List<Artist> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val withDataBinding: ArtistItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ArtistViewHolder.LAYOUT,
            parent,
            false)
        return ArtistViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.artist = artists[position]
            Picasso.get().load(artists[position].image).into(it.imageText)
        }
        /*holder.viewDataBinding.root.setOnClickListener {
            val action = ArtistFragmentDirections.actionArtistFragmentToArtistFragment()
            // Navigate using that action
            holder.viewDataBinding.root.findNavController().navigate(action)
        }*/
    }

    override fun getItemCount(): Int {
        return artists.size
    }


    class ArtistViewHolder(val viewDataBinding: ArtistItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.artist_item
        }
    }


}