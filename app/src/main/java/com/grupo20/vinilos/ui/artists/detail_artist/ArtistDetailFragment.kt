package com.grupo20.vinilos.ui.artists.detail_artist

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
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.imageview.ShapeableImageView
import com.grupo20.vinilos.R
import com.grupo20.vinilos.ui.artists.ArtistFragmentDirections
import java.util.concurrent.Executors

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ArtistDetailViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nombre: TextView = view.findViewById(R.id.nombre)
        val descripcion: TextView = view.findViewById(R.id.descripcion)
        val birth: TextView = view.findViewById(R.id.birth)
        val imagen: ImageView = view.findViewById(R.id.foto)
        val back: Button = view.findViewById(R.id.back)
        var navigator: NavController = findNavController()

        nombre.text = artist.name
        descripcion.text = artist.description

        back.setOnClickListener {
            val action = ArtistDetailFragmentDirections.actionArtistDetailFragmentToNavigationArtists()
            navigator?.navigate(action)
        }

        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        var image: Bitmap? = null

        executor.execute {
            try {
                val `in` = java.net.URL(artist.image).openStream()
                image = BitmapFactory.decodeStream(`in`)
                handler.post {
                    imagen.setImageBitmap(image)
                }
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}