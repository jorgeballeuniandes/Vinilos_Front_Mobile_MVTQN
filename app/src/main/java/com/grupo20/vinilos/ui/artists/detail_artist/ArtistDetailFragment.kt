package com.grupo20.vinilos.ui.artists.detail_artist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.google.android.material.imageview.ShapeableImageView
import com.grupo20.vinilos.R

class ArtistDetailFragment : Fragment() {

    private val args: ArtistDetailFragmentArgs by navArgs()
    val artist = args.artist
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
        val Nombre: TextView = view.findViewById(R.id.Nombre)
        val Imagen:ShapeableImageView = view.findViewById(R.id.image_text)
        Nombre.text = artist.name
        //Imagen.setBackgroundDrawable  (artist.image)
    }


}