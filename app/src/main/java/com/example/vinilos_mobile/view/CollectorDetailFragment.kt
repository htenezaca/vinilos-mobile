package com.example.vinilos_mobile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos_mobile.R
import com.example.vinilos_mobile.databinding.FragmentCollectorDetailBinding
import com.example.vinilos_mobile.model.models.Collector
import com.example.vinilos_mobile.model.models.CollectorDetail
import com.example.vinilos_mobile.viewmodel.CollectorDetailViewModel

class CollectorDetailFragment: Fragment(R.layout.fragment_collector_detail) {

    private var _binding: FragmentCollectorDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CollectorDetailViewModel

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        viewModel = ViewModelProvider(
            this,
            CollectorDetailViewModel.Factory(activity.application, arguments?.getInt("collectorId")!!)
        )[CollectorDetailViewModel::class.java]

        viewModel.collector.observe(viewLifecycleOwner, Observer<CollectorDetail> {
            it.apply {
                binding.collectorName.text = this.name
                binding.collectorEmail.text = this.email
                binding.collectorPhone.text = this.telephone
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

