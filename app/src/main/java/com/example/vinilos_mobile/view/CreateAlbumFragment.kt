package com.example.vinilos_mobile.view

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.webkit.URLUtil
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos_mobile.R
import com.example.vinilos_mobile.databinding.FragmentCreateAlbumBinding
import com.example.vinilos_mobile.model.models.Album
import com.example.vinilos_mobile.viewmodel.AlbumViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

class CreateAlbumFragment : Fragment(), View.OnClickListener {
    lateinit var binding: FragmentCreateAlbumBinding
    private lateinit var viewModel: AlbumViewModel

    private lateinit var nameEdit: TextInputEditText
    private lateinit var imageEdit: TextInputEditText
    private lateinit var dateEdit: TextInputEditText
    private lateinit var descriptionEdit: TextInputEditText
    private lateinit var genreDropdown: MaterialAutoCompleteTextView
    private lateinit var labelDropdown: MaterialAutoCompleteTextView
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
        binding = FragmentCreateAlbumBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_album, container, false)
    }

    private fun setupListeners(view: View) {
        nameEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                validateAlbumName(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        imageEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                validateAlbumImage(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        dateEdit.setOnFocusChangeListener(object : OnFocusChangeListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onFocusChange(p0: View?, p1: Boolean) {
                if (p1) {
                    val datePicker = MaterialDatePicker.Builder.datePicker()
                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                        .setTitleText("Seleccione fecha").build()
                    datePicker.show(childFragmentManager, datePicker.toString())

                    datePicker.addOnPositiveButtonClickListener {
                        val selectedDate =
                            ZonedDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneId.of("GMT"))

                        date.editText?.setText(
                            String.format(
                                "%s-%s-%s",
                                selectedDate.year,
                                selectedDate.monthValue,
                                selectedDate.dayOfMonth
                            )
                        )
                    }
                }
                Log.d("ola", "ola")
            }

        })

        dateEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                validateAlbumDate(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        descriptionEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                validateAlbumDescription(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(
            this@CreateAlbumFragment, AlbumViewModel.Factory(requireActivity().application)
        )[AlbumViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        nameEdit = view.findViewById<TextInputEditText>(R.id.album_name_edit)
        imageEdit = view.findViewById<TextInputEditText>(R.id.album_image_edit)
        dateEdit = view.findViewById<TextInputEditText>(R.id.album_date_edit)
        descriptionEdit = view.findViewById<TextInputEditText>(R.id.album_description_edit)
        genreDropdown = view.findViewById<MaterialAutoCompleteTextView>(R.id.genderDropdown)
        labelDropdown = view.findViewById<MaterialAutoCompleteTextView>(R.id.labelDropdown)

        name = view.findViewById<TextInputLayout>(R.id.new_album_name)
        image = view.findViewById<TextInputLayout>(R.id.new_album_image)
        date = view.findViewById<TextInputLayout>(R.id.new_album_date)
        description = view.findViewById<TextInputLayout>(R.id.new_album_description)
        genre = view.findViewById<TextInputLayout>(R.id.new_album_gender)
        label = view.findViewById<TextInputLayout>(R.id.new_album_label)

        val randomArray: Array<String> = arrayOf("Classical", "Salsa", "Rock", "Folk")
        genreDropdown.setSimpleItems(randomArray)

        val labelArray: Array<String> =
            arrayOf("Sony Music", "EMI", "Discos Fuentes", "Elektra", "Fania Records")
        labelDropdown.setSimpleItems(labelArray)

        setupListeners(view)

        date.setOnClickListener(this)

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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonpost -> {
                if (checkFields()) {
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
            }
            R.id.cancel_button -> {
                requireActivity().supportFragmentManager.popBackStack()
            }
            R.id.new_album_date -> {
                val datePicker = MaterialDatePicker.Builder.datePicker()
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds()).setTitleText("ola")
                    .build()
                datePicker.show(childFragmentManager, datePicker.toString())

                datePicker.addOnPositiveButtonClickListener {
                    val selectedDate =
                        ZonedDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneId.of("GMT"))

                    date.editText?.setText(
                        String.format(
                            "%s-%s-%s",
                            selectedDate.year,
                            selectedDate.monthValue,
                            selectedDate.dayOfMonth
                        )
                    )
                }
            }
        }
    }

    private fun checkFields(): Boolean {
        var partial = true
        if(!validateAlbumName(name.editText?.text.toString()))
            partial = false
        if(!validateAlbumDate(date.editText?.text.toString()))
            partial = false
        if(!validateAlbumImage(image.editText?.text.toString()))
            partial = false
        if(!validateAlbumDescription(description.editText?.text.toString()))
            partial = false
        if(!validateAlbumGenre(genre.editText?.text.toString()))
            partial = false
        if(!validateAlbumLabel(label.editText?.text.toString()))
            partial = false
        return partial
    }

    private fun validateAlbumName(text: String): Boolean {
        if (text.trim().isEmpty()) {
            name.error = "El campo es obligatorio"
            return false
        }
        name.error = null
        return true
    }

    private fun validateAlbumImage(text: String): Boolean {
        if (text.trim().isEmpty()) {
            image.error = "El campo es obligatorio"
            return false
        }
        if (!URLUtil.isValidUrl(text)) {
            image.error = "URL Inválida"
            return false
        }
        image.error = null
        return true
    }

    private fun validateAlbumDate(text: String): Boolean {

        if (text.trim().isEmpty()) {
            date.error = "El campo es obligatorio"
            return false
        }
        date.error = null
        return true
    }

    private fun validateAlbumDescription(text: String): Boolean {
        if (text.trim().isEmpty()) {
            description.error = "El campo es obligatorio"
            return false
        }
        description.error = null
        return true
    }

    private fun validateAlbumGenre(text: String): Boolean {
        if (text.trim().isEmpty()) {
            genre.error = "El campo es obligatorio"
            return false
        }
        genre.error = null
        return true
    }

    private fun validateAlbumLabel(text: String): Boolean {
        if (text.trim().isEmpty()) {
            label.error = "El campo es obligatorio"
            return false
        }
        label.error = null
        return true
    }
}