package com.grupo20.vinilos.ui.tracks.create_track

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.grupo20.vinilos.R

class CreateTrackFragment : Fragment() {

    companion object {
        fun newInstance() = CreateTrackFragment()
    }

    private lateinit var viewModel: CreateTrackViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_track, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CreateTrackViewModel::class.java)
        // TODO: Use the ViewModel
    }

}