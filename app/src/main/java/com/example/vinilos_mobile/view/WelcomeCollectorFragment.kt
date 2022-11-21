package com.example.vinilos_mobile.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
<<<<<<< HEAD
<<<<<<< HEAD
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos_mobile.R
import com.example.vinilos_mobile.databinding.FragmentWelcomeCollectorBinding
=======
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos_mobile.R
import com.example.vinilos_mobile.databinding.FragmentWelcomeCollectorBinding
import com.example.vinilos_mobile.model.models.Collector
>>>>>>> 95f5312 (Release/sprint1 (#63))
=======
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos_mobile.R
import com.example.vinilos_mobile.databinding.FragmentWelcomeCollectorBinding
>>>>>>> 627a67c (Release/sprint2 (#82))
import com.example.vinilos_mobile.viewmodel.CollectorViewModel

class WelcomeCollectorFragment : Fragment() {

    private var _binding: FragmentWelcomeCollectorBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CollectorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeCollectorBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }

        viewModel = ViewModelProvider(this, CollectorViewModel.Factory(activity.application))[CollectorViewModel::class.java]
        viewModel.collectors.observe(viewLifecycleOwner) {
            it.apply {
                val dropdown: AutoCompleteTextView =
                    activity.findViewById(R.id.collectorsWelcomeDropdown)

                val collectors = this.map { collector -> collector.name }
                val arrayAdapter =
                    ArrayAdapter(requireContext(), R.layout.dropdown_item, collectors)
                dropdown.setAdapter(arrayAdapter)

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
