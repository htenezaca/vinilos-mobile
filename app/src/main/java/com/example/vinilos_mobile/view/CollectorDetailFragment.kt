package com.example.vinilos_mobile.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vinilos_mobile.R
import com.example.vinilos_mobile.databinding.FragmentCollectorDetailBinding
import com.example.vinilos_mobile.model.models.CollectorDetail
import com.example.vinilos_mobile.viewmodel.CollectorDetailViewModel
import com.google.gson.Gson
import kotlinx.coroutines.launch

class CollectorDetailFragment: Fragment(R.layout.fragment_collector_detail) {

    private var _binding: FragmentCollectorDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CollectorDetailViewModel
    var iconCollector:String = "https://firebasestorage.googleapis.com/v0/b/storage-cdabb.appspot.com/o/%E2%86%B3%20Image.png?alt=media&token=b474bbd7-8a90-45e1-8cfa-fa35bb6cc600"

    companion object {
        fun newInstance(collectorId: Int): CollectorDetailFragment {
            val fragment = CollectorDetailFragment()
            val args = Bundle()
            args.putInt("collectorId", collectorId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCollectorDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("LongLogTag")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val recyclerView: RecyclerView = binding.favoritePerformersCollectorList
        recyclerView.layoutManager = LinearLayoutManager(context)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel = ViewModelProvider(
                    this@CollectorDetailFragment,
                    CollectorDetailViewModel.Factory(
                        activity.application,
                        arguments?.getInt("collectorId")!!
                    )
                )[CollectorDetailViewModel::class.java]

                viewModel.collector.observe(viewLifecycleOwner, Observer<CollectorDetail> {
                    it.apply {
                        binding.collectorName.text = this.name
                        binding.collectorEmail.text = this.email
                        binding.collectorPhone.text = this.telephone
                        Glide.with(this@CollectorDetailFragment)
                            .load(iconCollector)
                            .into(binding.collectorImageView)
                        Log.d("CollectorDetailFragment", Gson().toJson(this.collectorAlbums))
                        Log.d(
                            "PerformersCollectorDetailFragment",
                            Gson().toJson(this.favoritePerformers)
                        )

                        var performersList = this.favoritePerformers.toList()
                        binding.favoritePerformersCollectorList.adapter =
                            PerformersAdapter(performersList)


                    }
                })
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

