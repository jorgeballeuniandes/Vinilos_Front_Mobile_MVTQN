package com.grupo20.vinilos.ui.collectors

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.grupo20.vinilos.databinding.FragmentCollectorsBinding
import com.grupo20.vinilos.modelos.Collector

class CollectorsFragment : Fragment() {

    private lateinit var viewModel: CollectorsViewModel
    private var _binding: FragmentCollectorsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollectorsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CollectorsViewModel::class.java)

        val collectorsObserver = Observer<List<Collector>> { collectors ->
            binding.prueba = collectors[0]
        }

        viewModel.collectors.observe(viewLifecycleOwner, collectorsObserver)


    }

}