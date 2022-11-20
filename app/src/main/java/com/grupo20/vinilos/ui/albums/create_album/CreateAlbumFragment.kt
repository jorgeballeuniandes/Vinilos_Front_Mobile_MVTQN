package com.grupo20.vinilos.ui.albums.create_album

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.grupo20.vinilos.R

class CreateAlbumFragment : Fragment() {

    private lateinit var binding: CreateAlbumFragment

    companion object {
        fun newInstance() = CreateAlbumFragment()
    }

    private lateinit var viewModel: CreateAlbumViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val createview = inflater.inflate(R.layout.fragment_create_album, container, false)
        val generosArray = resources.getStringArray(R.array.list_generos)
        val autoCompleteGeneros = createview.findViewById<AutoCompleteTextView>(R.id.autoComplete_generos)
        val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_expandable_list_item_1, generosArray)
        autoCompleteGeneros?.setAdapter(adapter)

        val recordsArray = resources.getStringArray(R.array.list_records)
        val autoCompleteRecords = createview.findViewById<AutoCompleteTextView>(R.id.autoComplete_records)
        val recordAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_expandable_list_item_1, recordsArray)
        autoCompleteRecords?.setAdapter(recordAdapter)
        return createview
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CreateAlbumViewModel::class.java)
        // TODO: Use the ViewModel
    }

}