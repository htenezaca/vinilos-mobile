package com.example.vinilos_mobile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vinilos_mobile.R
import com.example.vinilos_mobile.databinding.FragmentAlbumListBinding
import com.example.vinilos_mobile.viewmodel.AlbumViewModel
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.vinilos_mobile.model.models.Album

class AlbumListFragment :Fragment() {


    private var _binding: FragmentAlbumListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : AlbumViewModel



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = AlbumsAdapter()
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }

        viewModel = ViewModelProvider(this, AlbumViewModel.Factory(activity.application)).get(AlbumViewModel::class.java)
        viewModel.albums.observe(viewLifecycleOwner, Observer<List<Album>> {
            it.apply {
                val listAlbums: AutoCompleteTextView = activity.findViewById(R.id.albumsList)

                val albums = this
                val arrayAdapter = ArrayAdapter(requireContext(), R.layout.album_item, albums)
                listAlbums.setAdapter(arrayAdapter)

            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}