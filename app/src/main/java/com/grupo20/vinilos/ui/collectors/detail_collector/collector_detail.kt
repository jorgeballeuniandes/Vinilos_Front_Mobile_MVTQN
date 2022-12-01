package com.grupo20.vinilos.ui.collectors.detail_collector

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.grupo20.vinilos.R
import com.grupo20.vinilos.ui.artists.detail_artist.ArtistDetailFragmentArgs
import java.text.SimpleDateFormat

@Suppress("DEPRECATION")
class CollectorDetailFragment : Fragment() {

private val args:CollectorDetailFragmentArgs by navArgs()
    private val  collector by lazy{
        args.collector
    }

    companion object {
        fun newInstance() = CollectorDetailFragment()
    }

    private lateinit var viewModel: CollectorDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_collector_detail, container, false)
    }

  @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CollectorDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }
    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val collectorName: TextView = view.findViewById(R.id.nombreCollector)
        val collectorTelphone: TextView = view.findViewById(R.id.telephoneCollector)
        val collectorEmail: TextView = view.findViewById(R.id.emailCollector)
        val back: Button = view.findViewById(R.id.back_collector)
        var navigator: NavController = findNavController()

        collectorName.text = collector.name
        collectorTelphone.text = collector.telephone
        collectorEmail.text = collector.email

        back.setOnClickListener {
            val action = CollectorDetailFragmentDirections.actionCollectorDetailToNavigationCollectors()
                navigator?.navigate(action)
        }
    }
}