package com.example.vinilos_mobile.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.example.vinilos_mobile.viewmodel.AddTrackAlbumViewModel
import com.example.vinilos_mobile.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AddTrackAlbumFragment : DialogFragment(R.layout.fragment_add_track_album) {


    private lateinit var viewModel: AddTrackAlbumViewModel
    private val albumId: Int by lazy { arguments?.getInt("albumId")!! }

    companion object {
        fun newInstance(albumId: Int): AddTrackAlbumFragment {
            val args = Bundle()
            args.putInt("albumId", albumId)
            val fragment = AddTrackAlbumFragment()
            fragment.arguments = args
            return fragment
        }
    }

    // Use 90% of the screen width
    override fun onResume() {
        super.onResume()
        val width = (resources.displayMetrics.widthPixels * 0.9).toInt()
        this.dialog?.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_track_album, container, false)
        val acceptBtn: Button = view.findViewById(R.id.track_add_accept)
        val job = Job()
        val uiScope = CoroutineScope(Dispatchers.Main + job)

        acceptBtn.setOnClickListener(View.OnClickListener {
            val name = view.findViewById<EditText>(R.id.track_add_name).text.toString()
            val minutes =
                view.findViewById<EditText>(R.id.track_add_duration_minute).text.toString()
            val seconds =
                view.findViewById<EditText>(R.id.track_add_duration_second).text.toString()
            if (name.isNotEmpty() && minutes.isNotEmpty() && seconds.isNotEmpty()) {

                // Launch the coroutine in the view model
                uiScope.launch {
                    val addTrack = viewModel.addTrack(name, minutes, seconds)
                    val result = "result"
                    setFragmentResult("requestAddTrack", bundleOf("1" to result))
                    dismiss()
                }
            } else {
                // Show an alert dialog saying that the fields are empty
                MaterialAlertDialogBuilder(requireContext()).setTitle("Error")
                    .setMessage("Por favor, rellena todos los campos")
                    .setPositiveButton("Ok") { dialog, which ->
                        // Respond to positive button press
                    }.show()
            }
        })

        val cancelBtn: Button = view.findViewById(R.id.track_add_cancel)
        cancelBtn.setOnClickListener(View.OnClickListener {
            // Close the fragment, no need to do anything else
            this.dismiss()
        })
        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        viewModel = ViewModelProvider(
            this@AddTrackAlbumFragment, AddTrackAlbumViewModel.Factory(
                activity.application, albumId
            )
        )[AddTrackAlbumViewModel::class.java]
    }

}