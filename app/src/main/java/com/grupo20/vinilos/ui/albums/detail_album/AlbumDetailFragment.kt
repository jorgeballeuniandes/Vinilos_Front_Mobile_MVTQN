package com.grupo20.vinilos.ui.albums.detail_album

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.grupo20.vinilos.R
import java.text.SimpleDateFormat

@Suppress("DEPRECATION")
class AlbumDetailFragment : Fragment() {

    private val args: AlbumDetailFragmentArgs by navArgs()
    private val album by lazy{
        args.album
    }

    companion object {
        fun newInstance() = AlbumDetailFragment()
    }

    private lateinit var viewModel: AlbumDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_album_detail, container, false)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AlbumDetailViewModel::class.java)
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val txt_album_name: TextView = view.findViewById(R.id.txt_album_name)
        val txt_album_description: TextView = view.findViewById(R.id.txt_album_description)
        val txt_album_release_date: TextView = view.findViewById(R.id.txt_album_release_date)
        val imagen: ImageView = view.findViewById(R.id.imv_album_cover)
        val back: Button = view.findViewById(R.id.btn_back)
        val navigator: NavController = findNavController()
        val format = SimpleDateFormat("yyyy-MM-dd")

        txt_album_name.text = album.name
        txt_album_description.text = album.description
        txt_album_release_date.text = "Release Date: " + format.format(album.releaseDate)

        back.setOnClickListener {
            val action = AlbumDetailFragmentDirections.actionAlbumDetailFragmentToNavigationAlbums()
            navigator.navigate(action)
        }

        Glide.with(this)
            .load(album.cover.toUri().buildUpon().scheme("https").build())
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_albums)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.drawable.ic_albums))
            .into(imagen)
        }

    }