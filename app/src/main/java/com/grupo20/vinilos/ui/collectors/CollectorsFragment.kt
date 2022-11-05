package com.grupo20.vinilos.ui.collectors

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.grupo20.vinilos.R

class CollectorsFragment : Fragment() {

    companion object {
        fun newInstance() = CollectorsFragment()
    }

    private lateinit var viewModel: CollectorsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_collectors, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CollectorsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}