package com.example.vinilos_mobile.view

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import com.bumptech.glide.Glide
import com.example.vinilos_mobile.R
import com.example.vinilos_mobile.databinding.FragmentAlbumDetailBinding
import com.example.vinilos_mobile.model.models.AlbumDetail
import com.example.vinilos_mobile.viewmodel.AlbumDetailViewModel
import kotlinx.coroutines.launch

class AlbumDetailFragment : Fragment(R.layout.fragment_album_detail) {

    private var _binding: FragmentAlbumDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AlbumDetailViewModel

    companion object {
        fun newInstance(albumId: Int): AlbumDetailFragment {
            val fragment = AlbumDetailFragment()
            val args = Bundle()
            args.putInt("albumId", albumId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAlbumDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        // Make description scrollable
        binding.albumDescriptionContent.movementMethod = ScrollingMovementMethod()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel = ViewModelProvider(
                    this@AlbumDetailFragment,
                    AlbumDetailViewModel.Factory(
                        activity.application,
                        arguments?.getInt("albumId")!!
                    )
                )[AlbumDetailViewModel::class.java]
                viewModel.album.observe(viewLifecycleOwner, Observer<AlbumDetail> {
                    it.apply {
                        _binding!!.albumName.text = name
                        _binding!!.artistName.text = performers.firstOrNull()?.name ?: "Unknown"
                        _binding!!.albumDescriptionContent.text = description
                        _binding!!.albumGenre.text = genre
                        _binding!!.albumYear.text = releaseDate.substring(0, 4)
                        Glide.with(this@AlbumDetailFragment)
                            .load(cover)
                            .diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.AUTOMATIC)
                            .into(_binding!!.albumCover)
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
