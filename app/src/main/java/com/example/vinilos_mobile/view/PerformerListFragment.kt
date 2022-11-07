package com.example.vinilos_mobile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos_mobile.databinding.FragmentPerformerListBinding
import com.example.vinilos_mobile.model.models.Performer
import com.example.vinilos_mobile.viewmodel.PerformerViewModel

class PerformerListFragment : Fragment() {


    private var _binding: FragmentPerformerListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: PerformerViewModel
    private var viewModelAdapter: PerformersAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this
        _binding = FragmentPerformerListBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = PerformersAdapter()
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView: RecyclerView = binding.performerRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }

        viewModel = ViewModelProvider(this, PerformerViewModel.Factory(activity.application)).get(PerformerViewModel::class.java)
        viewModel.performers.observe(viewLifecycleOwner, Observer<List<Performer>> {
            it.apply {
                viewModelAdapter!!.performers=this
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}