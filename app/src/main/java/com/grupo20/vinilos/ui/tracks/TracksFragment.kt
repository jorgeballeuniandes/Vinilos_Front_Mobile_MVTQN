package com.grupo20.vinilos.ui.tracks

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo20.vinilos.R
import com.grupo20.vinilos.databinding.FragmentAlbumBinding
import com.grupo20.vinilos.databinding.FragmentTracksBinding
import com.grupo20.vinilos.modelos.Album
import com.grupo20.vinilos.modelos.Track
import com.grupo20.vinilos.ui.albums.AlbumViewModel
import com.grupo20.vinilos.ui.albums.AlbumsAdapter

class TracksFragment : Fragment() {
    private var _binding: FragmentTracksBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: TracksViewModel
    private var viewModelAdapter: TrackAdapter? = null

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
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_tracks)
        viewModel = ViewModelProvider(this, TracksViewModel.Factory(activity.application))[TracksViewModel::class.java]
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
        viewModel = ViewModelProvider(this, TracksViewModel.Factory(requireActivity().application)).get(TracksViewModel::class.java)
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