package com.grupo20.vinilos.ui.artists.detail_artist

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import com.google.android.material.imageview.ShapeableImageView
import com.grupo20.vinilos.R
import com.grupo20.vinilos.ui.artists.ArtistFragmentDirections
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.concurrent.Executors

@Suppress("DEPRECATION")
class ArtistDetailFragment : Fragment() {

    private val args: ArtistDetailFragmentArgs by navArgs()
    private val artist by lazy{
        args.artist
    }
    companion object {
        fun newInstance() = ArtistDetailFragment()
    }

    private lateinit var viewModel: ArtistDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_artist_detail, container, false)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ArtistDetailViewModel::class.java)

    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nombre: TextView = view.findViewById(R.id.nombre)
        val descripcion: TextView = view.findViewById(R.id.descripcion)
        val birth: TextView = view.findViewById(R.id.birth)
        val imagen: ImageView = view.findViewById(R.id.foto)
        val back: Button = view.findViewById(R.id.back)
        val navigator: NavController = findNavController()
        val format = SimpleDateFormat("yyyy-MM-dd")

        nombre.text = artist.name
        descripcion.text = artist.description
        birth.text = "BirthDate: " + format.format(artist.birthDate)

        back.setOnClickListener {
            val action = ArtistDetailFragmentDirections.actionArtistDetailFragmentToNavigationArtists()
            navigator.navigate(action)
        }

        Glide.with(this)
            .load(artist.image.toUri().buildUpon().scheme("https").build())
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_artists_black_24dp)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.drawable.ic_artists_black_24dp))
            .into(imagen)

    }


}