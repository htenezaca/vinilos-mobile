package com.example.vinilos_mobile.view

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vinilos_mobile.R
import com.example.vinilos_mobile.databinding.FragmentMusicianDetailBinding
import com.example.vinilos_mobile.model.models.MusicianDetail
import com.example.vinilos_mobile.viewmodel.MusicianDetailViewModel
import kotlinx.coroutines.launch

class MusicianDetailFragment : Fragment(R.layout.fragment_musician_detail) {

    private var _binding: FragmentMusicianDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MusicianDetailViewModel

    companion object {
        fun newInstance(musicianId: Int): MusicianDetailFragment {
            val fragment = MusicianDetailFragment()
            val args = Bundle()
            args.putInt("musicianId", musicianId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMusicianDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val recyclerView: RecyclerView = binding.musicianRecyclerView
        val rotation = this.getResources().getConfiguration().orientation;
        if (rotation == 1) {
            recyclerView.layoutManager = GridLayoutManager(requireActivity().applicationContext, 2)
        } else {
            recyclerView.layoutManager = GridLayoutManager(requireActivity().applicationContext, 4)
        }
        //recyclerView.adapter = viewModelAdapter
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        // Make description scrollable
        binding.musicianDescription.movementMethod = ScrollingMovementMethod()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel = ViewModelProvider(
                    this@MusicianDetailFragment,
                    MusicianDetailViewModel.Factory(
                        activity.application,
                        arguments?.getInt("musicianId")!!
                    )
                )[MusicianDetailViewModel::class.java]
                viewModel.musician.observe(viewLifecycleOwner, Observer<MusicianDetail> {
                    it.apply {
                        _binding!!.musicianName.text = name
                        _binding!!.musicianBirthDate.text = birthDate.substring(0, 10)
                        _binding!!.musicianDescription.text = description
                        Glide.with(this@MusicianDetailFragment)
                            .load(image)
                            .diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.AUTOMATIC)
                            .into(_binding!!.musicianImage)

                        var albumList = this.albums.toList()
                        binding.musicianRecyclerView.adapter = AlbumsAdapter(albumList)
                    }
                })
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onNetworkError() {

    }
}
