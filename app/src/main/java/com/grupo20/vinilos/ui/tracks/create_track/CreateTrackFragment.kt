package com.grupo20.vinilos.ui.tracks.create_track

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
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.VolleyError
import com.google.android.material.textfield.TextInputLayout
import com.grupo20.vinilos.R
import com.grupo20.vinilos.modelos.Track
import com.grupo20.vinilos.ui.albums.AlbumsAdapter
import com.grupo20.vinilos.ui.tracks.TrackAdapter
import com.grupo20.vinilos.ui.tracks.TracksFragmentArgs
import com.grupo20.vinilos.ui.tracks.TracksFragmentDirections
import com.grupo20.vinilos.ui.tracks.TracksViewModel
import org.json.JSONObject

class CreateTrackFragment : Fragment() {

    private val args: CreateTrackFragmentArgs by navArgs()
    private val album by lazy{
        args.album
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val postButton = view.findViewById<Button>(R.id.btn_create_track)
        val back: Button = view.findViewById(R.id.btn_back)
        val navigator: NavController = findNavController()

        back.setOnClickListener {
            val action = CreateTrackFragmentDirections.actionCreateTrackFragmentToTracksFragment(album)
            navigator.navigate(action)
        }

        postButton.setOnClickListener {
            sendForm(view)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        viewModel = ViewModelProvider(this, CreateTrackViewModel.Factory(activity.application, album))[CreateTrackViewModel::class.java]
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
    }

    fun sendForm( createview:View){
        val name = createview.findViewById<TextInputLayout>(R.id.textInputLayout_name).editText?.text
        val duration = createview.findViewById<TextInputLayout>(R.id.textInputLayout_duration).editText?.text

        if (!name.isNullOrEmpty() && !duration.isNullOrEmpty()){
            val dataMap: HashMap<Any?, Any?> = HashMap<Any?, Any?>()

            dataMap["name"] = name.toString()
            dataMap["duration"] = duration.toString()
            viewModel.enviarFormulario(dataMap, ::onComplete, ::onError)

        }else{
            Toast.makeText(activity, "you need to fill all the parameters", Toast.LENGTH_SHORT).show()
        }
    }

    fun onComplete(resp: JSONObject){
        val navigator: NavController = findNavController()
        val action = CreateTrackFragmentDirections.actionCreateTrackFragmentToTracksFragment(album)
        Toast.makeText(activity, "Track has been added", Toast.LENGTH_SHORT).show()
        navigator.navigate(action)

    }
    fun onError(error: VolleyError){
        Toast.makeText(activity, "Error: " + error.networkResponse.data, Toast.LENGTH_SHORT).show()

    }
    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
        }
    }

}