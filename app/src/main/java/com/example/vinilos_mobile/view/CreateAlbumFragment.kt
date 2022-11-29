package com.example.vinilos_mobile.view

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.vinilos_mobile.R
import com.example.vinilos_mobile.model.models.Album
import com.example.vinilos_mobile.viewmodel.AlbumViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout

class CreateAlbumFragment : Fragment(), View.OnClickListener {

    private lateinit var viewModel: AlbumViewModel

    private lateinit var name: TextInputLayout
    private lateinit var image: TextInputLayout
    private lateinit var date: TextInputLayout
    private lateinit var description: TextInputLayout
    private lateinit var genre: TextInputLayout
    private lateinit var label: TextInputLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_album, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(
            this@CreateAlbumFragment, AlbumViewModel.Factory(requireActivity().application)
        )[AlbumViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        name = view.findViewById<TextInputLayout>(R.id.new_album_name)
        image = view.findViewById<TextInputLayout>(R.id.new_album_image)
        date = view.findViewById<TextInputLayout>(R.id.new_album_date)
        description = view.findViewById<TextInputLayout>(R.id.new_album_description)
        genre = view.findViewById<TextInputLayout>(R.id.new_album_gender)
        label = view.findViewById<TextInputLayout>(R.id.new_album_label)

        val btn: MaterialButton = view.findViewById(R.id.buttonpost)
        btn.setOnClickListener(this)

        val btnCancel: MaterialButton = view.findViewById(R.id.cancel_button)
        btnCancel.setOnClickListener(this)
    }

    companion object {
        fun newInstance() =
            CreateAlbumFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonpost -> {

                viewModel.postAlbum(
                    Album(
                        0,
                        name.editText?.text.toString(),
                        image.editText?.text.toString(),
                        date.editText?.text.toString(),
                        description.editText?.text.toString(),
                        genre.editText?.text.toString(),
                        label.editText?.text.toString(),
                    )
                )
                requireActivity().supportFragmentManager.popBackStack()
            }
            R.id.cancel_button -> {

                requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }
}