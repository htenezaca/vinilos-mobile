package com.example.vinilos_mobile.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.vinilos_mobile.R
import com.example.vinilos_mobile.databinding.FragmentWelcomeCollectorBinding
import com.example.vinilos_mobile.viewmodel.CollectorViewModel

class WelcomeCollectorFragment : Fragment(), View.OnClickListener {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn: Button = view.findViewById(R.id.enter_collector)
        btn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.enter_collector -> {
                val action = WelcomeCollectorFragmentDirections.actionWelcomeCollectorFragmentToAlbumActivity()

                v.findNavController().navigate(action)
            }

        }
    }

}
