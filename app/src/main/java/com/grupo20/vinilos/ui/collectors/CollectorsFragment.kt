package com.grupo20.vinilos.ui.collectors


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo20.vinilos.databinding.FragmentCollectorsBinding
import com.grupo20.vinilos.R
import com.grupo20.vinilos.modelos.Collector

@Suppress("DEPRECATION")
class CollectorsFragment : Fragment() {

    private var _binding: FragmentCollectorsBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: CollectorsViewModel
    private var viewModelAdapter: CollectorsAdapter? = null


  override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCollectorsBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = CollectorsAdapter()
        viewModelAdapter?.navigator = findNavController()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.listCollectors
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
    }

    @Deprecated("Deprecated in Java")
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