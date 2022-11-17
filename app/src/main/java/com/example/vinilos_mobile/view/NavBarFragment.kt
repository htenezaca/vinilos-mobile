package com.example.vinilos_mobile.view

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.core.content.res.ResourcesCompat
import com.example.vinilos_mobile.R

class NavBarFragment : Fragment(), View.OnClickListener {

    var orange: Int = 0
    var gray: Int = Color.argb(255, 146, 146, 146)
    var white: Int = Color.argb(255, 255, 255, 255)
    lateinit var btnAlbums: ImageButton;
    lateinit var btnArtists: ImageButton;
    lateinit var btnCollectors: ImageButton;

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

        orange = ResourcesCompat.getColor(resources, R.color.main_orange, null)

        btnArtists = view.findViewById(R.id.buttonIconArtists)
        btnArtists.setOnClickListener(this)

        btnAlbums = view.findViewById(R.id.buttonIconAlbums)
        btnAlbums.setOnClickListener(this)

        btnCollectors = view.findViewById(R.id.buttonIconCollectors)
        btnCollectors.setOnClickListener(this)

        btnAlbums.performClick()
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.buttonIconCollectors -> {
                val fragmentManager = requireActivity().supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.fragment_main_view, PerformerListFragment())
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()

                v.setBackgroundColor(orange)
                btnCollectors.setColorFilter(white)
                btnAlbums.setBackgroundColor(Color.argb(0, 255, 255, 255))
                btnAlbums.setColorFilter(gray)
                btnArtists.setBackgroundColor(Color.argb(0, 255, 255, 255))
                btnArtists.setColorFilter(gray)
            }
            R.id.buttonIconAlbums -> {
                val fragmentManager = requireActivity().supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.fragment_main_view, AlbumListFragment())
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()

                v.setBackgroundColor(orange)
                btnAlbums.setColorFilter(white)
                btnArtists.setBackgroundColor(Color.argb(0, 255, 255, 255))
                btnArtists.setColorFilter(gray)
                btnCollectors.setBackgroundColor(Color.argb(0, 255, 255, 255))
                btnCollectors.setColorFilter(gray)
            }
            R.id.buttonIconArtists -> {
                val fragmentManager = requireActivity().supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.fragment_main_view, PerformerListFragment())
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()

                v.setBackgroundColor(orange)
                btnArtists.setColorFilter(white)
                btnAlbums.setBackgroundColor(Color.argb(0, 255, 255, 255))
                btnAlbums.setColorFilter(gray)
                btnCollectors.setBackgroundColor(Color.argb(0, 255, 255, 255))
                btnCollectors.setColorFilter(gray)
            }
        }
    }

}
