package com.grupo20.vinilos.ui.albums.create_album

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.android.volley.VolleyError
import com.google.android.material.textfield.TextInputLayout
import com.grupo20.vinilos.R
import org.json.JSONObject
import com.grupo20.vinilos.ui.albums.AlbumsAdapter

class CreateAlbumFragment : Fragment() {

    private lateinit var binding: CreateAlbumFragment
    private var albumsViewModelAdapter: AlbumsAdapter? = null

    companion object {
        fun newInstance() = CreateAlbumFragment()
    }

    private lateinit var viewModel: CreateAlbumViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        albumsViewModelAdapter = AlbumsAdapter()
        val createview = inflater.inflate(R.layout.fragment_create_album, container, false)
        val generosArray = resources.getStringArray(R.array.list_generos)
        val autoCompleteGeneros = createview.findViewById<AutoCompleteTextView>(R.id.autoComplete_generos)
        val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_expandable_list_item_1, generosArray)
        autoCompleteGeneros?.setAdapter(adapter)

        val recordsArray = resources.getStringArray(R.array.list_records)
        val autoCompleteRecords = createview.findViewById<AutoCompleteTextView>(R.id.autoComplete_records)
        val recordAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_expandable_list_item_1, recordsArray)
        autoCompleteRecords?.setAdapter(recordAdapter)

        val postButton = createview.findViewById<Button>(R.id.btn_create_track_Album)
        postButton.setOnClickListener { view ->
            sendFrom(createview)
        }
        return createview
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CreateAlbumViewModel::class.java)
        // TODO: Use the ViewModel
    }

    fun sendFrom( createview:View){
        val name = createview.findViewById<TextInputLayout>(R.id.textInputLayout_name).editText?.text
        val cover = createview.findViewById<TextInputLayout>(R.id.textInputLayout_urlCover).editText?.text
        val description = createview.findViewById<TextInputLayout>(R.id.textInputLayout_description).editText?.text
        val releaseDate = createview.findViewById<TextInputLayout>(R.id.textInputLayout_date).editText?.text
        val record = createview.findViewById<AutoCompleteTextView>(R.id.autoComplete_records).text
        val genders = createview.findViewById<AutoCompleteTextView>(R.id.autoComplete_generos).text

        if (!name.isNullOrEmpty() && !cover.isNullOrEmpty() && !description.isNullOrEmpty()
            && !releaseDate.isNullOrEmpty() && !record.isNullOrEmpty() && !genders.isNullOrEmpty()){
            val dataMap: HashMap<Any?, Any?> = HashMap<Any?, Any?>()
            dataMap.put("name", name.toString())
            dataMap.put("cover",cover.toString())
            dataMap.put("description",description.toString())
            dataMap.put("releaseDate",releaseDate.toString())
            dataMap.put("recordLabel",record.toString())
            dataMap.put("genre",genders.toString())
            //"id":100,"name":"a","cover":"a","releaseDate":"1984-08-01T00:00:00.000Z","description":"a","genre":"a","recordLabel":"a"}
            viewModel.enviarFormulario(dataMap, ::onComplete, ::onError)



        }else{
            Toast.makeText(activity, "you need to fill all the parameters", Toast.LENGTH_SHORT).show()

        }
    }

    fun onComplete(resp: JSONObject){
        Toast.makeText(activity, "Your album has been created", Toast.LENGTH_SHORT).show()
        albumsViewModelAdapter?.notifyDataSetChanged()
        this.findNavController().navigate(R.id.navigation_create_album)

    }
    fun onError(error: VolleyError){
        Toast.makeText(activity, "Error: " + error.networkResponse.data, Toast.LENGTH_SHORT).show()

    }

}