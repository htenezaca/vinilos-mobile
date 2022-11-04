package com.example.vinilos_mobile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.vinilos_mobile.R

class WelcomeGuestFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_welcome_guest, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn: Button = view.findViewById(R.id.userIsCollectorButton)
        btn.setOnClickListener(this)

        val btn2: Button = view.findViewById(R.id.userIsGuestButton)
        btn2.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.userIsCollectorButton -> {
                val action = WelcomeGuestFragmentDirections.actionWelcomeGuestFragmentToWelcomeCollectorFragment()

                v.findNavController().navigate(action)
            }

            R.id.userIsGuestButton -> {
                val action = WelcomeGuestFragmentDirections.actionWelcomeGuestFragmentToAlbumActivity()
                v.findNavController().navigate(action)
            }

        }


    }
}