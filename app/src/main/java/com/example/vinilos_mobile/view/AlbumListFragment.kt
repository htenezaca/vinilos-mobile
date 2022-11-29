package com.example.vinilos_mobile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.vinilos_mobile.R
import com.example.vinilos_mobile.databinding.FragmentAlbumListBinding
import com.example.vinilos_mobile.model.models.Album
import com.example.vinilos_mobile.viewmodel.AlbumViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch


class AlbumListFragment :Fragment(), View.OnClickListener {

    private var _binding: FragmentAlbumListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : AlbumViewModel
    private var viewModelAdapter: AlbumsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumListBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = AlbumsAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.albumRecyclerView
        val rotation = this.resources.configuration.orientation
        if (rotation == 1) {
            recyclerView.layoutManager = GridLayoutManager(requireActivity().applicationContext, 3)
        } else {
            //Increase the area of the recycler view
            recyclerView.layoutManager = GridLayoutManager(requireActivity().applicationContext, 4)
        }
        recyclerView.adapter = viewModelAdapter

        val btn: FloatingActionButton = view.findViewById(R.id.buttonnew)
        btn.setOnClickListener(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel = ViewModelProvider(
                    this@AlbumListFragment, AlbumViewModel.Factory(activity.application)
                )[AlbumViewModel::class.java]
                viewModel.albums.observe(viewLifecycleOwner, Observer {
                    it.apply {
                        viewModelAdapter!!.albums = this
                    }
                })
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun postAlbum(newAlbum: Album) {

        viewModel.postAlbum(newAlbum)
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.buttonnew -> {

                val fragmentManager = requireActivity().supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                val fragment = CreateAlbumFragment.newInstance()
                // Add the current fragment to the back stack
                fragmentTransaction.replace(R.id.fragment_main_view, fragment)
                    .addToBackStack("Collector List").commit()
            }

        }

    }
}
