package com.grupo20.vinilos.ui.collectors

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo20.vinilos.databinding.FragmentCollectorsBinding
import com.grupo20.vinilos.modelos.Collector

class CollectorsFragment : Fragment() {

    private var _binding: FragmentCollectorsBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: CollectorsViewModel
    private var viewModelAdapter: CollectorsAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollectorsBinding.inflate(inflater, container, false)
        viewModelAdapter = CollectorsAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.listCollectors
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CollectorsViewModel::class.java)

        val collectorsObserver = Observer<List<Collector>> { collectors ->
            collectors.apply {
                viewModelAdapter!!.collectors = this
            }
        }

        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
        viewModel.collectors.observe(viewLifecycleOwner, collectorsObserver)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            //Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            Log.e("Vinilos", "Error de red")
            viewModel.onNetworkErrorShown()
        }
    }

}