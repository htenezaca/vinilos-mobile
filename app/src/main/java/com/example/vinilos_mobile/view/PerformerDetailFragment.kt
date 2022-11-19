package com.example.vinilos_mobile.view

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vinilos_mobile.R
import com.example.vinilos_mobile.databinding.FragmentPerformerDetailBinding
import com.example.vinilos_mobile.model.models.*
import com.example.vinilos_mobile.viewmodel.PerformerDetailViewModel
import kotlinx.coroutines.launch

class PerformerDetailFragment : Fragment(R.layout.fragment_performer_detail) {

    private var _binding: FragmentPerformerDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: PerformerDetailViewModel

    companion object {
        fun newInstance(performerId: Int, performerType: PerformerType?): PerformerDetailFragment {
            val fragment = PerformerDetailFragment()
            val args = Bundle()
            args.putInt("performerId", performerId)
            args.putSerializable("performerType", performerType)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPerformerDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val recyclerView: RecyclerView = binding.performerDetailRecyclerView
        val rotation = this.resources.configuration.orientation
        if (rotation == 1) {
            recyclerView.layoutManager = GridLayoutManager(requireActivity().applicationContext, 3)
        } else {
            recyclerView.layoutManager = GridLayoutManager(requireActivity().applicationContext, 6)
        }
        //recyclerView.adapter = viewModelAdapter
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        // Make description scrollable
        binding.performerDetailDescription.movementMethod = ScrollingMovementMethod()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel = ViewModelProvider(
                    this@PerformerDetailFragment,
                    PerformerDetailViewModel.Factory(
                        activity.application,
                        arguments?.getInt("performerId")!!,
                        arguments?.getSerializable("performerType")!! as PerformerType
                    )
                )[PerformerDetailViewModel::class.java]
                viewModel.performer.observe(viewLifecycleOwner, Observer {
                    it.apply {
                        _binding!!.performerDetailName.text = name
                        _binding!!.performerDetailDate.text = when(it) {
                            is BandDetail -> it.creationDate.substring(0, 10)
                            is MusicianDetail -> it.birthDate.substring(0, 10)
                            else -> ""
                        }
                        _binding!!.performerDetailDescription.text = description
                        Glide.with(this@PerformerDetailFragment)
                            .load(image)
                            .diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.AUTOMATIC)
                            .into(_binding!!.performerDetailImage)

                        val albumList = this.albums.toList()
                        binding.performerDetailRecyclerView.adapter = AlbumsAdapter(albumList)
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
