package com.example.vinilos_mobile.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.navigation.findNavController
import com.example.vinilos_mobile.R

class NavBarFragment : Fragment(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nav_bar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn: ImageButton = view.findViewById(R.id.buttonIconCollectors)
        btn.setOnClickListener(this)

        val btn2: ImageButton = view.findViewById(R.id.buttonIconAlbums)
        btn2.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.buttonIconCollectors -> {
                val action = AlbumListFragmentDirections.actionAlbumListToAlbumDetail()

            }

        }
    }
}