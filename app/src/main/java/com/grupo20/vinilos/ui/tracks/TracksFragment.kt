package com.grupo20.vinilos.ui.tracks

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo20.vinilos.databinding.FragmentTracksBinding
import com.grupo20.vinilos.modelos.Track
import com.grupo20.vinilos.ui.albums.detail_album.AlbumDetailFragmentDirections


class TracksFragment : Fragment() {
    private var _binding: FragmentTracksBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: TracksViewModel
    private var viewModelAdapter: TrackAdapter? = null

    private val args: TracksFragmentArgs by navArgs()
    private val album by lazy{
        args.album
    }

    companion object {
        fun newInstance() = TracksFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTracksBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = TrackAdapter()
        viewModelAdapter?.navigator = findNavController()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.tracksRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter

        val back: Button = view.findViewById(com.grupo20.vinilos.R.id.btn_back)
        val add_tracks: Button = view.findViewById(com.grupo20.vinilos.R.id.btn_add_track)
        val navigator: NavController = findNavController()

        back.setOnClickListener {
            val action = TracksFragmentDirections.actionTracksFragmentToAlbumDetailFragment(album)
            navigator.navigate(action)
        }

        add_tracks.setOnClickListener {
            val action = TracksFragmentDirections.actionTracksFragmentToCreateTrackFragment(album)
            navigator.navigate(action)
        }

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(com.grupo20.vinilos.R.string.title_tracks)
        viewModel = ViewModelProvider(this, TracksViewModel.Factory(activity.application, album))[TracksViewModel::class.java]
        viewModel.tracks.observe(viewLifecycleOwner, Observer<List<Track>> {
            it.apply {
                viewModelAdapter!!.tracks = this
            }
        })
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshDataFromNetwork()
        viewModel = ViewModelProvider(this, TracksViewModel.Factory(requireActivity().application, album)).get(TracksViewModel::class.java)
        viewModel.tracks.observe(viewLifecycleOwner, Observer<List<Track>> {
            it.apply {
                viewModelAdapter!!.tracks = this
            }
        })
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}